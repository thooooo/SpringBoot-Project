package fr.efrei.pokemon_tcg.dto;

import java.time.LocalDateTime;
import fr.efrei.pokemon_tcg.models.Trade;

public class TradeDTO {
    private String uuid;
    private String sourceDresseurUuid;
    private String targetDresseurUuid;
    private String sourceCardUuid;
    private String targetCardUuid;
    private LocalDateTime dateEchange;

    public TradeDTO() {}

    public TradeDTO(Trade trade) {
        this.uuid = trade.getUuid();
        this.sourceDresseurUuid = trade.getSourceDresseur().getUuid();
        this.targetDresseurUuid = trade.getTargetDresseur().getUuid();
        this.sourceCardUuid = trade.getSourceCard().getUuid();
        this.targetCardUuid = trade.getTargetCard().getUuid();
        this.dateEchange = trade.getDateEchange();
    }

    
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSourceDresseurUuid() {
        return sourceDresseurUuid;
    }

    public void setSourceDresseurUuid(String sourceDresseurUuid) {
        this.sourceDresseurUuid = sourceDresseurUuid;
    }

    public String getTargetDresseurUuid() {
        return targetDresseurUuid;
    }

    public void setTargetDresseurUuid(String targetDresseurUuid) {
        this.targetDresseurUuid = targetDresseurUuid;
    }

    public String getSourceCardUuid() {
        return sourceCardUuid;
    }

    public void setSourceCardUuid(String sourceCardUuid) {
        this.sourceCardUuid = sourceCardUuid;
    }

    public String getTargetCardUuid() {
        return targetCardUuid;
    }

    public void setTargetCardUuid(String targetCardUuid) {
        this.targetCardUuid = targetCardUuid;
    }

    public LocalDateTime getDateEchange() {
        return dateEchange;
    }

    public void setDateEchange(LocalDateTime dateEchange) {
        this.dateEchange = dateEchange;
    }
}