package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.dto.TradeDTO;
import java.util.List;

public interface ITradeService {

    List<TradeDTO> findAllTrades();

    List<TradeDTO> historiqueDresseurTrade(String dresseurUuid);

    List<TradeDTO> tradesByYear(int annee);

    List<TradeDTO> tradesByMonth(int annee, int mois);

    List<TradeDTO> tradesByDay(int annee, int mois, int jour);
}