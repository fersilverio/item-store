package com.api.itemstore.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.itemstore.dtos.ItemDto;
import com.api.itemstore.models.Item;
import com.api.itemstore.repositories.ItemRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

@Controller
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @PostMapping("/items")
    public ResponseEntity<Item> creaItem(@RequestBody @Valid ItemDto dto) {
        var item = new Item();
        BeanUtils.copyProperties(dto, item);
        return ResponseEntity.status(HttpStatus.CREATED).body((itemRepository.save(item)));
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Object> getOneItem(@PathVariable(value = "id") UUID id) {
        Optional<Item> itemO = itemRepository.findById(id);

        if (itemO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(itemO.get());

    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.status(HttpStatus.OK).body(itemRepository.findAll());
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Object> updateOneItem(
            @PathVariable(value = "id") UUID id,
            @RequestBody @Valid ItemDto dto) {

        Optional<Item> itemO = itemRepository.findById(id);

        if (itemO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }

        var itemModel = itemO.get();

        BeanUtils.copyProperties(dto, itemModel);
        return ResponseEntity.status(HttpStatus.OK).body(itemRepository.save(itemModel));

    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Object> deleteOneItem(@PathVariable(value = "id") UUID id) {
        Optional<Item> itemO = itemRepository.findById(id);
        if (itemO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        }
        itemRepository.delete(itemO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Item deleted successfully.");
    }

}