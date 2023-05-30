package com.example.item;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
@Tag(name = "ItemController", description = "Endpoints for getting all items and items by id")
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController (ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Operation(summary = "Get all items")
    @GetMapping("/items")
    public List<Item> getAllItems () {
        return this.itemRepository.findAll();
    }

    @Operation(summary = "Get a specifik item by ID number")
    @GetMapping ("/item/{id}")
    public Item getItemById (@Min(0) @PathVariable long id) {
        return this.itemRepository.findById(id).orElse(null);
    }
    @Operation(summary = "Add a new item")
    @PostMapping("/item/add")
    public String addNewItem (@Valid @RequestBody Item item) {
        this.itemRepository.save(item);
        return "Item " + item.getName() + " created";
    }

}
