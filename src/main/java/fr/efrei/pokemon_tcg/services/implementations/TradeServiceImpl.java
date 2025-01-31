package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.dto.TradeDTO;
import fr.efrei.pokemon_tcg.repositories.TradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import fr.efrei.pokemon_tcg.services.IDresseurService;  
import fr.efrei.pokemon_tcg.services.ITradeService;
import fr.efrei.pokemon_tcg.models.Dresseur;

@Service
public class TradeServiceImpl implements ITradeService {
    private final TradeRepository tradeRepository;
    private final IDresseurService dresseurService;

    public TradeServiceImpl(TradeRepository tradeRepository, IDresseurService dresseurService) {
        this.tradeRepository = tradeRepository;
        this.dresseurService = dresseurService;
    }

    @Override
    public List<TradeDTO> findAllTrades() {
        return tradeRepository.findAll().stream()
            .map(TradeDTO::new)
            .collect(Collectors.toList());
    }

    @Override
    public List<TradeDTO> historiqueDresseurTrade(String dresseurUuid) {
        Dresseur dresseur = dresseurService.findById(dresseurUuid);
        return tradeRepository.findBySourceDresseurOrTargetDresseur(dresseur, dresseur).stream()
            .map(TradeDTO::new)
            .collect(Collectors.toList());
    }

    @Override
    public List<TradeDTO> tradesByYear(int annee) {
        return tradeRepository.findTradesByYear(annee).stream()
            .map(TradeDTO::new)
            .collect(Collectors.toList());
    }

    @Override
    public List<TradeDTO> tradesByMonth(int annee, int mois) {
        return tradeRepository.findTradesByMonthAndYear(annee, mois).stream()
            .map(TradeDTO::new)
            .collect(Collectors.toList());
    }

    @Override
    public List<TradeDTO> tradesByDay(int annee, int mois, int jour) {
        return tradeRepository.findTradesByDayMonthAndYear(annee, mois, jour).stream()
            .map(TradeDTO::new)
            .collect(Collectors.toList());
    }
}