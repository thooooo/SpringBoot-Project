package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.models.Item;
import fr.efrei.pokemon_tcg.repositories.ItemRepository;
import fr.efrei.pokemon_tcg.services.IItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements IItemService {

	private final ItemRepository repository;

	public ItemServiceImpl(ItemRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Item> findAll() {
		return repository.findAll();
	}

	@Override
	public Item findById(String uuid) {
		return repository.findById(uuid).orElse(null);
	}

	@Override
	public void save(Item item) {
		repository.save(item);
	}
}
