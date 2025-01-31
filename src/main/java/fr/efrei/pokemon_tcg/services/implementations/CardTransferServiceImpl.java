package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.models.Card;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.constants.DeckType;
import fr.efrei.pokemon_tcg.dto.BattleResultDTO;
import fr.efrei.pokemon_tcg.dto.CardTransferDTO;
import fr.efrei.pokemon_tcg.services.ICardService;  
import fr.efrei.pokemon_tcg.services.IDresseurService;
import fr.efrei.pokemon_tcg.services.ICardTransferService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Comparator;

@Service
public class CardTransferServiceImpl implements ICardTransferService {
    private final IDresseurService dresseurService;
    private final ICardService cardService;

    public CardTransferServiceImpl(IDresseurService dresseurService, ICardService cardService) {
        this.dresseurService = dresseurService;
        this.cardService = cardService;
    }

    @Override
    public boolean transferCard(String dresseurUuid, CardTransferDTO transferDTO) {

        Dresseur dresseur = dresseurService.findById(dresseurUuid);
        if (dresseur == null) {
            throw new IllegalArgumentException("Dresseur non trouvé");
        }

        Card card = cardService.findById(transferDTO.getCardUuid());
        if (card == null) {
            throw new IllegalArgumentException("Carte non trouvée");
        }

        if (!card.getDresseur().getUuid().equals(dresseurUuid)) {
            throw new IllegalStateException("La carte n'appartient pas au dresseur");
        }

        DeckType sourceDeck = DeckType.valueOf(transferDTO.getSourceDeck());
        DeckType targetDeck = DeckType.valueOf(transferDTO.getTargetDeck());

        if (card.getDeckType() != sourceDeck) {
            throw new IllegalStateException("La carte n'est pas dans le deck source spécifié");
        }

        if (!canTransferCard(dresseur, card, targetDeck)) {
            throw new IllegalStateException("Impossible de transférer la carte");
        }

        card.setDeckType(targetDeck);
        cardService.save(card);

        return true;
    }

    @Override
    public boolean canTransferCard(Dresseur dresseur, Card card, DeckType targetDeck) {
        if (targetDeck == DeckType.Principal) {
            long principalDeckSize = dresseur.getCartesPrincipales().size();
            return principalDeckSize < 5;
        }
        return true;
    }

    public BattleResultDTO battleDresseurs(String challengerUuid, String challengedUuid) {
        Dresseur challenger = dresseurService.findById(challengerUuid);
        Dresseur challenged = dresseurService.findById(challengedUuid);

        if (challenger == null || challenged == null) {
            throw new IllegalArgumentException("Un des dresseurs n'existe pas");
        }

        List<Card> challengerCombatDeck = challenger.getCartesPrincipales();
        List<Card> challengedCombatDeck = challenged.getCartesPrincipales();

        if ((challengerCombatDeck.isEmpty() || challengedCombatDeck.isEmpty()) || (challengerCombatDeck.size() < 5 || challengedCombatDeck.size() < 5)) {
            throw new IllegalStateException("Un des dresseurs n'a pas ou pas suffisament de cartes de combat");
        }

        double challengerStrength = calculateDeckStrength(challengerCombatDeck);
        double challengedStrength = calculateDeckStrength(challengedCombatDeck);

        boolean challengerWins = Math.random() < (challengerStrength / (challengerStrength + challengedStrength));

        Dresseur winner = challengerWins ? challenger : challenged;
        Dresseur loser = challengerWins ? challenged : challenger;

        Card bestCard = loser.getCartesPrincipales().stream()
            .max(Comparator.comparingInt(Card::getRarity))
            .orElseThrow(() -> new IllegalStateException("Aucune carte trouvée"));

        bestCard.setDresseur(winner);
        bestCard.setDeckType(DeckType.Secondaire);
        cardService.save(bestCard);

        BattleResultDTO battleResult = new BattleResultDTO();
        battleResult.setWinner(winner);
        battleResult.setLoser(loser);
        battleResult.setTransferredCard(bestCard);
        battleResult.setWinnerStrength(challengerWins ? challengerStrength : challengedStrength);
        battleResult.setLoserStrength(challengerWins ? challengedStrength : challengerStrength);
        battleResult.setRandomized(true);

        return battleResult;
    }

    private double calculateDeckStrength(List<Card> deck) {
        return deck.stream()
            .mapToDouble(card -> {
                double rarityFactor = card.getRarity() * 2.0;
                return rarityFactor;
            })
            .sum();
    }
}