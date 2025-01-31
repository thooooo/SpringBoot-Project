package fr.efrei.pokemon_tcg.repositories;

import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, String> {

    @SuppressWarnings("null")
    List<Trade> findAll();

    List<Trade> findBySourceDresseurOrTargetDresseur(Dresseur sourceDresseur, Dresseur targetDresseur);

    @Query("SELECT t FROM Trade t WHERE YEAR(t.dateEchange) = :annee")
    List<Trade> findTradesByYear(@Param("annee") int annee);

    @Query("SELECT t FROM Trade t WHERE YEAR(t.dateEchange) = :annee AND MONTH(t.dateEchange) = :mois")
    List<Trade> findTradesByMonthAndYear(@Param("annee") int annee, @Param("mois") int mois);

    @Query("SELECT t FROM Trade t WHERE YEAR(t.dateEchange) = :annee AND MONTH(t.dateEchange) = :mois AND DAY(t.dateEchange) = :jour")
    List<Trade> findTradesByDayMonthAndYear(@Param("annee") int annee, @Param("mois") int mois, @Param("jour") int jour);
}