package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.models.Item;
import fr.efrei.pokemon_tcg.services.IItemService;
import fr.efrei.pokemon_tcg.services.implementations.ItemServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

	private final IItemService itemService;

	public ItemController(ItemServiceImpl itemService) {
		this.itemService = itemService;
	}

	@GetMapping
	public ResponseEntity<List<Item>> findAll() {
		return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Item item) {
		itemService.save(item);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
