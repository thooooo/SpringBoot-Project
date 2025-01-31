package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.dto.CardTransferDTO;
import fr.efrei.pokemon_tcg.dto.BattleResultDTO;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Card;
import fr.efrei.pokemon_tcg.constants.DeckType;

public interface ICardTransferService {

    boolean transferCard(String dresseurUuid, CardTransferDTO transferDTO);

    boolean canTransferCard(Dresseur dresseur, Card card, DeckType targetDeck);

    BattleResultDTO battleDresseurs(String challengerUuid, String challengedUuid);
}