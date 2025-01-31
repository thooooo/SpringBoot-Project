package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.constants.TypePokemon;
import fr.efrei.pokemon_tcg.controllers.PokemonController;
import fr.efrei.pokemon_tcg.dto.CreatePokemon;
import fr.efrei.pokemon_tcg.models.Pokemon;

import java.util.List;

public interface IPokemonService {

	List<Pokemon> findAll(TypePokemon type);

	Pokemon findById(String uuid);

	void create(CreatePokemon pokemon);

	boolean update(String uuid, Pokemon pokemon);

	boolean delete(String uuid);
}
