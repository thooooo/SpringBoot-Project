package fr.efrei.pokemon_tcg.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trades")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "source_dresseur_uuid", nullable = false)
    private Dresseur sourceDresseur;

    @ManyToOne
    @JoinColumn(name = "target_dresseur_uuid", nullable = false)
    private Dresseur targetDresseur;

    @ManyToOne
    @JoinColumn(name = "source_card_uuid", nullable = false)
    private Card sourceCard;

    @ManyToOne
    @JoinColumn(name = "target_card_uuid", nullable = false)
    private Card targetCard;

    @Column(nullable = false)
    private LocalDateTime dateEchange;

    public Trade() {
        this.dateEchange = LocalDateTime.now();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Dresseur getSourceDresseur() {
        return sourceDresseur;
    }

    public void setSourceDresseur(Dresseur sourceDresseur) {
        this.sourceDresseur = sourceDresseur;
    }

    public Dresseur getTargetDresseur() {
        return targetDresseur;
    }

    public void setTargetDresseur(Dresseur targetDresseur) {
        this.targetDresseur = targetDresseur;
    }

    public Card getSourceCard() {
        return sourceCard;
    }

    public void setSourceCard(Card sourceCard) {
        this.sourceCard = sourceCard;
    }

    public Card getTargetCard() {
        return targetCard;
    }

    public void setTargetCard(Card targetCard) {
        this.targetCard = targetCard;
    }

    public LocalDateTime getDateEchange() {
        return dateEchange;
    }

    public void setDateEchange(LocalDateTime dateEchange) {
        this.dateEchange = dateEchange;
    }
}