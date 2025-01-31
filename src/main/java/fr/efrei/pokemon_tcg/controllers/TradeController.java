package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.dto.TradeDTO;
import fr.efrei.pokemon_tcg.services.IDresseurService;
import fr.efrei.pokemon_tcg.services.ITradeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/trade")
public class TradeController {
    private final IDresseurService dresseurService;
    private final ITradeService tradeService;

    public TradeController(IDresseurService dresseurService, ITradeService tradeService) {
        this.dresseurService = dresseurService;
        this.tradeService = tradeService;
    }

    @PostMapping
    public ResponseEntity<?> tradeCard(@Valid @RequestBody TradeDTO tradeDTO) {
        try {
            dresseurService.tradeCard(tradeDTO);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/historique")
    public ResponseEntity<List<TradeDTO>> findAllTrades() {
        return ResponseEntity.ok(tradeService.findAllTrades());
    }

    @GetMapping("/historique/dresseur/{dresseurUuid}")
    public ResponseEntity<List<TradeDTO>> historiqueDresseurTrade(@PathVariable String dresseurUuid) {
        return ResponseEntity.ok(tradeService.historiqueDresseurTrade(dresseurUuid));
    }

    @GetMapping("/historique/annee/{annee}")
    public ResponseEntity<List<TradeDTO>> tradesByYear(@PathVariable int annee) {
        return ResponseEntity.ok(tradeService.tradesByYear(annee));
    }

    @GetMapping("/historique/annee/{annee}/mois/{mois}")
    public ResponseEntity<List<TradeDTO>> tradesByMonth(@PathVariable int annee, @PathVariable int mois) {
        return ResponseEntity.ok(tradeService.tradesByMonth(annee, mois));
    }

    @GetMapping("/historique/annee/{annee}/mois/{mois}/jour/{jour}")
    public ResponseEntity<List<TradeDTO>> tradesByDay(@PathVariable int annee, @PathVariable int mois, @PathVariable int jour) {
        return ResponseEntity.ok(tradeService.tradesByDay(annee, mois, jour));
    }
}