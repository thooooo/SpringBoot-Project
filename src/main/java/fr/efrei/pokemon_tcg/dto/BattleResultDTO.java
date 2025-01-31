package fr.efrei.pokemon_tcg.dto;

import fr.efrei.pokemon_tcg.models.Card;
import fr.efrei.pokemon_tcg.models.Dresseur;

public class BattleResultDTO {
    private Dresseur winner;
    private Dresseur loser;
    private Card transferredCard;
    private double winnerStrength;
    private double loserStrength;
    private boolean randomized;

    public BattleResultDTO() {}

    public Dresseur getWinner() {
        return winner;
    }

    public void setWinner(Dresseur winner) {
        this.winner = winner;
    }

    public Dresseur getLoser() {
        return loser;
    }

    public void setLoser(Dresseur loser) {
        this.loser = loser;
    }

    public Card getTransferredCard() {
        return transferredCard;
    }

    public void setTransferredCard(Card transferredCard) {
        this.transferredCard = transferredCard;
    }

    public double getWinnerStrength() {
        return winnerStrength;
    }

    public void setWinnerStrength(double winnerStrength) {
        this.winnerStrength = winnerStrength;
    }

    public double getLoserStrength() {
        return loserStrength;
    }

    public void setLoserStrength(double loserStrength) {
        this.loserStrength = loserStrength;
    }

    public boolean isRandomized() {
        return randomized;
    }

    public void setRandomized(boolean randomized) {
        this.randomized = randomized;
    }

    @Override
    public String toString() {
        return String.format("Gagnant: %s %s %.2f - Perdant: %.2f %s %s \n Carte échangée: %s", 
            winner.getNom(), 
            winner.getPrenom(), 
            winnerStrength, 
            loserStrength, 
            loser.getNom(), 
            loser.getPrenom(), 
            transferredCard.getPokemon().getNom());
    }
}