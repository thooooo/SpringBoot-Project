package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.dto.CapturePokemon;
import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.dto.TradeDTO;
import fr.efrei.pokemon_tcg.models.Card;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.models.Trade;
import fr.efrei.pokemon_tcg.repositories.DresseurRepository;
import fr.efrei.pokemon_tcg.repositories.TradeRepository;
import fr.efrei.pokemon_tcg.services.ICardService;
import fr.efrei.pokemon_tcg.services.IDresseurService;
import fr.efrei.pokemon_tcg.services.IPokemonService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DresseurServiceImpl implements IDresseurService {

 	private final DresseurRepository repository;
    private final IPokemonService pokemonService;
    private final ICardService cardService;
	private final TradeRepository tradeRepository;

    public DresseurServiceImpl(DresseurRepository repository, PokemonServiceImpl pokemonService, ICardService cardService, TradeRepository tradeRepository) {
        this.repository = repository;
        this.pokemonService = pokemonService;
        this.cardService = cardService;
        this.tradeRepository = tradeRepository;
    }

	@Override
	public List<Dresseur> findAll() {
		return repository.findAllByDeletedAtNull();
	}

	@Override
	public Dresseur findById(String uuid) {
		return repository.findById(uuid).orElse(null);
	}

	public void capturerPokemon(String uuid, CapturePokemon capturePokemon) {
		Dresseur dresseur = findById(uuid);
		Pokemon pokemon = pokemonService.findById(capturePokemon.getUuid());
		dresseur.getPokemonList().add(pokemon);
		repository.save(dresseur);
	}

	@Override
	public void create(DresseurDTO dresseurDTO) {
		Dresseur dresseur = new Dresseur();
		dresseur.setNom(dresseurDTO.getNom());
		dresseur.setPrenom(dresseurDTO.getPrenom());
		dresseur.setDeletedAt(null);
		repository.save(dresseur);
	}

	@Override
	public boolean update(String uuid, DresseurDTO dresseurDTO) {
		return false;
	}

	@Override
	public boolean delete(String uuid) {
		Dresseur dresseur = findById(uuid);
		dresseur.setDeletedAt(LocalDateTime.now());
		repository.save(dresseur);
		return true;
	}

	@Override
    public List<Card> drawCards(String dresseurUuid, int numberOfCards) {
        Dresseur dresseur = findById(dresseurUuid);
        if (dresseur == null) {
            throw new IllegalArgumentException("Dresseur non trouvé");
        }

        LocalDateTime now = LocalDateTime.now();
        if (dresseur.getLastCardDrawTime() != null) {
            LocalDateTime lastDrawTime = dresseur.getLastCardDrawTime();
            long hoursSinceLastDraw = ChronoUnit.HOURS.between(lastDrawTime, now);
            
            if (hoursSinceLastDraw < 24) {
                throw new IllegalStateException("Vous ne pouvez tirer des cartes qu'une seule fois par jour. Prochain tirage possible le " 
                    + lastDrawTime.plusHours(24).toLocalDate() + " à " + lastDrawTime.plusHours(24).toLocalTime());
            }
        }
        
        List<Card> drawnCards = cardService.drawMultipleRandomCards(dresseur, numberOfCards);
        
        dresseur.setLastCardDrawTime(now);
        repository.save(dresseur);
        
        return drawnCards;
    }

    @Override
    public List<Card> getCards(String dresseurUuid) {
        Dresseur dresseur = findById(dresseurUuid);
        if (dresseur == null) {
            throw new IllegalArgumentException("Dresseur non trouvé");
        }
        
        return cardService.getCardsByDresseur(dresseur);
    }

	@Override
	public boolean tradeCard(TradeDTO tradeDTO) {
		Dresseur sourceDresseur = findById(tradeDTO.getSourceDresseurUuid());
		Dresseur targetDresseur = findById(tradeDTO.getTargetDresseurUuid());

		if (sourceDresseur == null || targetDresseur == null) {
			throw new IllegalArgumentException("Un ou les dresseurs n'ont pas été trouvé");
		}

		LocalDateTime now = LocalDateTime.now();
		
		if (sourceDresseur.getLastTradeTime() != null) {
			LocalDateTime lastTradeTime = sourceDresseur.getLastTradeTime();
			long hoursSinceLastTrade = ChronoUnit.HOURS.between(lastTradeTime, now);
			
			if (hoursSinceLastTrade < 24) {
				throw new IllegalStateException(
					"Le dresseur " + sourceDresseur.getNom() + " ne peut échanger qu'une seule fois par jour. " +
					"Prochain échange possible le " + lastTradeTime.plusHours(24).toLocalDate() + 
					" à " + lastTradeTime.plusHours(24).toLocalTime()
				);
			}
		}

		if (targetDresseur.getLastTradeTime() != null) {
			LocalDateTime lastTradeTime = targetDresseur.getLastTradeTime();
			long hoursSinceLastTrade = ChronoUnit.HOURS.between(lastTradeTime, now);
			
			if (hoursSinceLastTrade < 24) {
				throw new IllegalStateException(
					"Le dresseur " + targetDresseur.getNom() + " ne peut échanger qu'une seule fois par jour. " +
					"Prochain échange possible le " + lastTradeTime.plusHours(24).toLocalDate() + 
					" à " + lastTradeTime.plusHours(24).toLocalTime()
				);
			}
		}

		Card sourceCard = cardService.findById(tradeDTO.getSourceCardUuid());
		Card targetCard = cardService.findById(tradeDTO.getTargetCardUuid());

		if (sourceCard == null || targetCard == null) {
			throw new IllegalArgumentException("Une ou deux cartes n'ont pas été trouvé");
		}

		if (!sourceCard.getDresseur().getUuid().equals(sourceDresseur.getUuid()) || !targetCard.getDresseur().getUuid().equals(targetDresseur.getUuid())) {
			throw new IllegalStateException("Cartes introuvables dans l'inventaire du dresseur");
		}

		sourceCard.setDresseur(targetDresseur);
		targetCard.setDresseur(sourceDresseur);
		cardService.save(sourceCard);
		cardService.save(targetCard);

		sourceDresseur.setLastTradeTime(now);
		targetDresseur.setLastTradeTime(now);
		repository.save(sourceDresseur);
		repository.save(targetDresseur);

		Trade trade = new Trade();
		trade.setSourceDresseur(sourceDresseur);
		trade.setTargetDresseur(targetDresseur);
		trade.setSourceCard(sourceCard);
		trade.setTargetCard(targetCard);
		
		tradeRepository.save(trade);

		return true;
	}
}