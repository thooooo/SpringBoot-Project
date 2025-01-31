package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.dto.CardDTO;
import fr.efrei.pokemon_tcg.models.Card;
import fr.efrei.pokemon_tcg.services.IDresseurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/card")
public class CardController {

    private final IDresseurService dresseurService;

    public CardController(IDresseurService dresseurService) {
        this.dresseurService = dresseurService;
    }

    @GetMapping("/{dresseurUuid}/draw")
    public ResponseEntity<?> drawCards(@PathVariable String dresseurUuid, @RequestParam(defaultValue = "5") int numberOfCards) {
        try {
            List<Card> drawnCards = dresseurService.drawCards(dresseurUuid, numberOfCards);

            List<CardDTO> cardDTOs = drawnCards.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
            
            return ResponseEntity.ok(cardDTOs);
            
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{dresseurUuid}")
    public ResponseEntity<List<CardDTO>> getCards(@PathVariable String dresseurUuid) {
        List<Card> cards = dresseurService.getCards(dresseurUuid);

        List<CardDTO> cardDTOs = cards.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(cardDTOs);
    }

    private CardDTO convertToDTO(Card card) {
        CardDTO dto = new CardDTO();

        dto.setUuid(card.getUuid());
        dto.setPokemon(card.getPokemon());
        dto.setAttacks(card.getAttacks());
        dto.setRarity(card.getRarity());
        
        return dto;
    }
}