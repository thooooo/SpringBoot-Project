package fr.efrei.pokemon_tcg.dto;

import fr.efrei.pokemon_tcg.models.Pokemon;

import java.util.List;

public class CardDTO {
    private String uuid;
    private Pokemon pokemon;
    private List<String> attacks;
    private int rarity;

    public CardDTO() {}

    public CardDTO(String uuid, Pokemon pokemon, List<String> attacks, int rarity) {
        this.uuid = uuid;
        this.pokemon = pokemon;
        this.attacks = attacks;
        this.rarity = rarity;
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
}