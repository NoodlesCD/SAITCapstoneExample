package com.mro.quotation.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public List<Item> getItemsByNameContaining(String name) {
        return itemRepository.findByNameContaining(name);
    }

    public void addNewItem(Item item) {
        itemRepository.save(item);
    }

    public void updateItem(Long id, Item updatedItem) {
        itemRepository.findById(id).map(item -> {
           item.setSku(updatedItem.getSku());
           item.setName(updatedItem.getName());
           item.setCost(updatedItem.getCost());
           item.setComplexityFactor(updatedItem.getComplexityFactor());
           item.setComplexityDescription(updatedItem.getComplexityDescription());
           return itemRepository.save(item);
        });
    }
}
