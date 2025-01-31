package fr.efrei.pokemon_tcg.dto;

import java.time.LocalDateTime;
import fr.efrei.pokemon_tcg.models.Trade;
import jakarta.validation.constraints.NotNull;

public class TradeDTO {
    private String uuid;

    @NotNull(message = "L'UUID du dresseur source ne peut pas être nul")
    private String sourceDresseurUuid;

    @NotNull(message = "L'UUID du dresseur cible ne peut pas être nul")
    private String targetDresseurUuid;

    @NotNull(message = "L'UUID de la carte source ne peut pas être nul")
    private String sourceCardUuid;

    @NotNull(message = "L'UUID de la carte cible ne peut pas être nul")
    private String targetCardUuid;

    @NotNull(message = "La date de l'echange ne peut pas être nulle")
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