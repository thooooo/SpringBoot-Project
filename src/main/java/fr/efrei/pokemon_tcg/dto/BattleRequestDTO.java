package fr.efrei.pokemon_tcg.dto;

import jakarta.validation.constraints.NotNull;

public class BattleRequestDTO {

    @NotNull(message = "L'UUID du dresseur challenger ne peut pas être nul")
    private String challengerUuid;

    @NotNull(message = "L'UUID du dresseur challenged ne peut pas être nul")
    private String challengedUuid;

    public String getChallengerUuid() {
        return challengerUuid;
    }

    public void setChallengerUuid(String challengerUuid) {
        this.challengerUuid = challengerUuid;
    }

    public String getChallengedUuid() {
        return challengedUuid;
    }

    public void setChallengedUuid(String challengedUuid) {
        this.challengedUuid = challengedUuid;
    }
}