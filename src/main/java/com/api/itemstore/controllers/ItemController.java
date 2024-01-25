package com.api.itemstore.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}