package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.models.Card;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.repositories.CardRepository;
import fr.efrei.pokemon_tcg.repositories.PokemonRepository;
import fr.efrei.pokemon_tcg.services.ICardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements ICardService {
    private final CardRepository cardRepository;
    private final PokemonRepository pokemonRepository;

    public CardServiceImpl(CardRepository cardRepository, PokemonRepository pokemonRepository) {
        this.cardRepository = cardRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public Card drawRandomCard(Dresseur dresseur) {

        List<Pokemon> allPokemon = pokemonRepository.findAll();
        
        Random random = new Random();
        Pokemon randomPokemon = allPokemon.get(random.nextInt(allPokemon.size()));

        Card card = new Card();
        card.setPokemon(randomPokemon);
        card.setDresseur(dresseur);
        
        List<String> attacks = List.of("Charge", "Griffe", "Tonnerre", "Flammèche", "Pistolet à Eau", "Fouet", "Psyko", "Laser Glace", "Vive-Attaque", "Déflagration");
        card.setAttacks(
            random.ints(0, attacks.size())
                  .distinct()
                  .limit(2)
                  .mapToObj(attacks::get)
                  .collect(Collectors.toList())
        );

        return cardRepository.save(card);
    }

    @Override
    public List<Card> drawMultipleRandomCards(Dresseur dresseur, int numberOfCards) {
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            drawnCards.add(drawRandomCard(dresseur));
        }
        return drawnCards;
    }

    @Override
    public List<Card> getCardsByDresseur(Dresseur dresseur) {
        return cardRepository.findByDresseur(dresseur);
    }

    @Override
    public Card findById(String uuid) {
        return cardRepository.findById(uuid).orElse(null);
    }

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card deleteCard(Card card) {
        cardRepository.delete(card);
        return card;
    }
}