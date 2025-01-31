package fr.efrei.pokemon_tcg.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Dresseur {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String nom;
    private String prenom;

    @OneToMany(mappedBy = "dresseur")
    @JsonIgnore 
    private List<Pokemon> pokemonList;

    @OneToMany(mappedBy = "dresseur")
    @JsonManagedReference  
    private List<Card> cardList;

    @OneToMany(mappedBy = "dresseur")
    @JsonManagedReference 
    private List<Card> cartesPrincipales;

    @OneToMany(mappedBy = "dresseur")
    @JsonManagedReference 
    private List<Card> cartesSecondaires;

    private LocalDateTime deletedAt;

    private LocalDateTime lastCardDrawTime;

    private LocalDateTime lastTradeTime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    public LocalDateTime getLastCardDrawTime() {
        return lastCardDrawTime;
    }

    public void setLastCardDrawTime(LocalDateTime lastCardDrawTime) {
        this.lastCardDrawTime = lastCardDrawTime;
    }

    public LocalDateTime getLastTradeTime() {
        return lastTradeTime;
    }

    public void setLastTradeTime(LocalDateTime lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    public boolean canAddToMainDeck() {
        return cartesPrincipales.size() < 5;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public List<Card> getCartesPrincipales() {
        return cartesPrincipales;
    }

    public void setCartesPrincipales(List<Card> cartesPrincipales) {
        this.cartesPrincipales = cartesPrincipales;
    }

    public List<Card> getCartesSecondaires() {
        return cartesSecondaires;
    }

    public void setCartesSecondaires(List<Card> cartesSecondaires) {
        this.cartesSecondaires = cartesSecondaires;
    }
}
