package fr.efrei.pokemon_tcg.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;
import java.util.Random;
import fr.efrei.pokemon_tcg.constants.DeckType;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @ManyToOne
    @JsonBackReference  
    private Pokemon pokemon;

    @ElementCollection
    private List<String> attacks;

    @Column(nullable = false)
    private int rarity; 

    @ManyToOne
    @JsonBackReference  
    private Dresseur dresseur;

    @Enumerated(EnumType.STRING)
    private DeckType deckType;

    public Card() {

        Random random = new Random();
        double rarityRoll = random.nextDouble();
        
        if (rarityRoll < 0.05) {
            this.rarity = 5;
        } else if (rarityRoll < 0.15) {
            this.rarity = 4; 
        } else if (rarityRoll < 0.30) {
            this.rarity = 3; 
        } else if (rarityRoll < 0.50) {
            this.rarity = 2;
        } else {
            this.rarity = 1;
        }

        this.deckType = DeckType.Secondaire;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public List<String> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<String> attacks) {
        this.attacks = attacks;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public Dresseur getDresseur() {
        return dresseur;
    }

    public void setDresseur(Dresseur dresseur) {
        this.dresseur = dresseur;
    }

    public DeckType getDeckType() {
        return deckType;
    }

    public void setDeckType(DeckType deckType) {
        this.deckType = deckType;
    }
}