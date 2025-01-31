package fr.efrei.pokemon_tcg.dto;

import jakarta.validation.constraints.NotNull;

public class CapturePokemon {

	@NotNull(message = "L'UUID du pokémon ne peut pas être nul")
	private String uuid;

	public String getUuid() {
		return uuid;
	}
}
