package fr.efrei.pokemon_tcg.dto;

public class BattleRequestDTO {
    private String challengerUuid;
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