package com.api.itemstore.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.itemstore.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}