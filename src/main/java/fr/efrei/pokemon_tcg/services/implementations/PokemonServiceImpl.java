package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.constants.TypePokemon;
import fr.efrei.pokemon_tcg.dto.CreatePokemon;
import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.repositories.PokemonRepository;
import fr.efrei.pokemon_tcg.services.IPokemonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonServiceImpl implements IPokemonService {

	private final PokemonRepository repository;

	public PokemonServiceImpl(PokemonRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Pokemon> findAll(TypePokemon typePokemon) {
		if(typePokemon == null) {
			return repository.findAll();
		}
		return repository.findAllByType(typePokemon);
	}

	@Override
	public void create(CreatePokemon pokemon) {
		Pokemon pokemonACreer = new Pokemon();
		pokemonACreer.setType(pokemon.getType());
		pokemonACreer.setNom(pokemon.getNom());
		pokemonACreer.setNiveau(pokemon.getNiveau());
		repository.save(pokemonACreer);
	}

	@Override
	public Pokemon findById(String uuid) {
		// renvoie soit un pokemon soit null
		return repository.findById(uuid).orElse(null);
	}

	@Override
	public boolean update(String uuid, Pokemon pokemon) {
		Pokemon pokemonAModifier = findById(uuid);
		if(pokemonAModifier == null) {
			return false;
		}
		pokemonAModifier.setNom(pokemon.getNom());
		pokemonAModifier.setNiveau(pokemon.getNiveau());
		pokemonAModifier.setType(pokemon.getType());
		repository.save(pokemonAModifier);
		return true;
	}

	@Override
	public boolean delete(String uuid) {
		Pokemon pokemonAModifier = findById(uuid);
		if(pokemonAModifier == null) {
			return false;
		}
		repository.deleteById(uuid);
		return true;
	}
}
