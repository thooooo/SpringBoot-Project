package fr.efrei.pokemon_tcg.dto;

import jakarta.validation.constraints.NotNull;

public class CardTransferDTO {
    
    @NotNull(message = "L'UUID du carton ne peut pas être nul")
    private String cardUuid;

    @NotNull(message = "L'UUID du deck source ne peut pas être nul")
    private String sourceDeck; 

    @NotNull(message = "L'UUID du deck cible ne peut pas être nul")
    private String targetDeck;

    public String getCardUuid() {
        return cardUuid;
    }

    public void setCardUuid(String cardUuid) {
        this.cardUuid = cardUuid;
    }

    public String getSourceDeck() {
        return sourceDeck;
    }

    public void setSourceDeck(String sourceDeck) {
        this.sourceDeck = sourceDeck;
    }

    public String getTargetDeck() {
        return targetDeck;
    }

    public void setTargetDeck(String targetDeck) {
        this.targetDeck = targetDeck;
    } 
}