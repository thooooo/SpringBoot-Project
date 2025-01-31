package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.models.Card;
import fr.efrei.pokemon_tcg.models.Dresseur;

import java.util.List;

public interface ICardService {
    Card drawRandomCard(Dresseur dresseur);
    
    List<Card> drawMultipleRandomCards(Dresseur dresseur, int numberOfCards);

    List<Card> getCardsByDresseur(Dresseur dresseur);

    Card findById(String uuid);

    Card save(Card card);

    Card deleteCard(Card card);
}