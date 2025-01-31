package fr.efrei.pokemon_tcg.repositories;

import fr.efrei.pokemon_tcg.constants.TypePokemon;
import fr.efrei.pokemon_tcg.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, String> {

	// SELECT * FROM pokemon WHERE type_pokemon = :type;
	List<Pokemon> findAllByType(TypePokemon type);
}
