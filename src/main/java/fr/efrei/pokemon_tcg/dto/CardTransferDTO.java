package fr.efrei.pokemon_tcg.dto;

public class CardTransferDTO {
    
    private String cardUuid;
    private String sourceDeck; 
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