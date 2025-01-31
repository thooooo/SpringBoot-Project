package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.models.Item;

import java.util.List;

public interface IItemService {

	List<Item> findAll();

	Item findById(String uuid);

	void save(Item item);
}
