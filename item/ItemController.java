package com.mro.quotation.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/items")
@CrossOrigin("*")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getItems() {
        return itemService.getItems();
    }

    @GetMapping("/containingname")
    public List<Item> getItemsByNameContaining(@PathVariable(value = "name") String name) {
        return itemService.getItemsByNameContaining(name);
    }

    @PostMapping("/additem")
    public void addItem(@RequestBody Item item) {
        itemService.addNewItem(item);
    }

    @PutMapping("/edititem")
    public void editItem(@PathVariable(value = "id") Long id, @RequestBody Item item) {
        itemService.updateItem(id, item);
    }
}
