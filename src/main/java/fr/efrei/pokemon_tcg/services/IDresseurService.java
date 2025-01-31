package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.dto.CapturePokemon;
import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.dto.TradeDTO;
import fr.efrei.pokemon_tcg.models.Card;
import fr.efrei.pokemon_tcg.models.Dresseur;

import java.util.List;

public interface IDresseurService {
    List<Dresseur> findAll();

    Dresseur findById(String uuid);

    void create(DresseurDTO dresseurDTO);

    boolean update(String uuid, DresseurDTO dresseurDTO);

    boolean delete(String uuid);

    void capturerPokemon(String uuid, CapturePokemon capturePokemon);

    List<Card> drawCards(String dresseurUuid, int numberOfCards);

    List<Card> getCards(String dresseurUuid);

	boolean tradeCard(TradeDTO tradeDTO);
}