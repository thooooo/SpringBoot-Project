package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.dto.CapturePokemon;
import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.dto.BattleResultDTO;
import fr.efrei.pokemon_tcg.dto.BattleRequestDTO;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.services.IDresseurService;
import fr.efrei.pokemon_tcg.services.ICardTransferService;
import fr.efrei.pokemon_tcg.services.implementations.DresseurServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dresseurs")
public class DresseurController {

	private final IDresseurService dresseurService;
	private final ICardTransferService cardTransferService;

	public DresseurController(DresseurServiceImpl dresseurService, ICardTransferService cardTransferService) {
		this.dresseurService = dresseurService;
		this.cardTransferService = cardTransferService;
	}

	@GetMapping
	public ResponseEntity<List<Dresseur>> findAll() {
		return new ResponseEntity<>(dresseurService.findAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody DresseurDTO dresseurDTO) {
		dresseurService.create(dresseurDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity<?> delete(@PathVariable String uuid) {
		dresseurService.delete(uuid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PatchMapping("/{uuid}/capturer")
	public ResponseEntity<?> capturer(
			@PathVariable String uuid,
			@RequestBody CapturePokemon capturePokemon
	) {
		dresseurService.capturerPokemon(uuid, capturePokemon);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/bataille")
	public ResponseEntity<?> bataille(@RequestBody BattleRequestDTO battleRequest) {
		
		String challengerUuid = battleRequest.getChallengerUuid();
		String challengedUuid = battleRequest.getChallengedUuid();
		
		if (challengerUuid == null || challengedUuid == null) {
			return ResponseEntity.badRequest().body("UUIDs des dresseurs manquants");
		}

		try {
			BattleResultDTO battleResult = cardTransferService.battleDresseurs(challengerUuid, challengedUuid);
			return ResponseEntity.ok(battleResult.toString());
		} catch (IllegalArgumentException | IllegalStateException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
