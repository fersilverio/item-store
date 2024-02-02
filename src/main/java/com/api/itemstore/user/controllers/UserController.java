package com.api.itemstore.user.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.itemstore.user.dtos.UserDto;
import com.api.itemstore.user.models.User;
import com.api.itemstore.user.repositories.UserRepository;

import jakarta.validation.Valid;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDto dto) {
        var user = new User();
        BeanUtils.copyProperties(dto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body((userRepository.save(user)));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id) {
        Optional<User> itemO = userRepository.findById(id);

        if (itemO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(itemO.get());

    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateOneUser(
            @PathVariable(value = "id") UUID id,
            @RequestBody @Valid UserDto dto) {

        Optional<User> userO = userRepository.findById(id);
        if (userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        var userModel = userO.get();

        BeanUtils.copyProperties(dto, userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(userModel));

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteOneUser(@PathVariable(value = "id") UUID id) {
        Optional<User> userO = userRepository.findById(id);
        if (userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userRepository.delete(userO.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }
}