package fr.efrei.pokemon_tcg.dto;

import fr.efrei.pokemon_tcg.constants.TypePokemon;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class CreatePokemon {

	@Length(min = 3, max = 20)
	@NotNull(message = "Le nom du pokémon ne peut pas être nul")
	private String nom;

	@Min(value = 1, message = "Le niveau du pokémon doit être supérieur ou égal à 1")
	private Integer niveau;

	@NotNull(message = "Le type du pokémon ne peut pas être nul")
	private TypePokemon type;

	public String getNom() {
		return nom;
	}

	public Integer getNiveau() {
		return niveau;
	}

	public TypePokemon getType() {
		return type;
	}
}
