package com.example.item;

import com.example.item.Item;
import com.example.item.ItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController (ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/items")
    public List<Item> getAllItems () {
        return this.itemRepository.findAll();
    }

    @GetMapping ("/item/{id}")
    public Item getItemById (@PathVariable long id) {
        return this.itemRepository.findById(id).orElse(null);
    }

    @PostMapping("/item/add")
    public String addNewItem (@RequestBody Item item) {
        this.itemRepository.save(item);
        return "Item " + item.getName() + " created";
    }

}
