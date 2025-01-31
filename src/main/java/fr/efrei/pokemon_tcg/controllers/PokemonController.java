package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.constants.TypePokemon;
import fr.efrei.pokemon_tcg.dto.CreatePokemon;
import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.services.IPokemonService;
import fr.efrei.pokemon_tcg.services.implementations.PokemonServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {
	private final IPokemonService pokemonService;

	public PokemonController(PokemonServiceImpl pokemonService) {
		this.pokemonService = pokemonService;
	}

	@GetMapping
	public ResponseEntity<List<Pokemon>> getAll(@RequestParam(required = false) TypePokemon type) {
		return new ResponseEntity<>(pokemonService.findAll(type), HttpStatus.OK);
	}

	@GetMapping("/{uuid}")
	public ResponseEntity<Pokemon> getById(@PathVariable String uuid) {
		Pokemon pokemon = pokemonService.findById(uuid);
		if(pokemon == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(pokemonService.findById(uuid), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createPokemon(@Valid @RequestBody CreatePokemon pokemon) {
		pokemonService.create(pokemon);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/{uuid}")
	public ResponseEntity<?> updatePokemon(@PathVariable String uuid, @RequestBody Pokemon pokemon) {
		boolean isModifier = pokemonService.update(uuid, pokemon);
		if(!isModifier) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity<?> updatePokemon(@PathVariable String uuid) {
		boolean isSupprimer = pokemonService.delete(uuid);
		if(!isSupprimer) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
