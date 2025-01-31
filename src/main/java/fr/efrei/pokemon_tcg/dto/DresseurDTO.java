package fr.efrei.pokemon_tcg.dto;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotNull;

public class DresseurDTO {

	@Length(min = 3, max = 20)
	@NotNull(message = "Le nom du dresseur ne peut pas être nul")
	private String nom;

	@Length(min = 3, max = 20)
	@NotNull(message = "Le prenom du dresseur ne peut pas être nul")
	private String prenom;

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}
}
