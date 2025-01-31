package fr.efrei.pokemon_tcg.repositories;

import fr.efrei.pokemon_tcg.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
}
