package fr.efrei.pokemon_tcg.models;

import jakarta.persistence.*;
import fr.efrei.pokemon_tcg.constants.TypePokemon;

@Entity
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String nom;

    @Enumerated(EnumType.STRING)
    private TypePokemon type;

    private int niveau;

    @ManyToOne
    @JoinColumn(name = "dresseur_uuid")
    private Dresseur dresseur;

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

    public TypePokemon getType() {
        return type;
    }

    public void setType(TypePokemon type) {
        this.type = type;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public Dresseur getDresseur() {
        return dresseur;
    }

    public void setDresseur(Dresseur dresseur) {
        this.dresseur = dresseur;
    }
}