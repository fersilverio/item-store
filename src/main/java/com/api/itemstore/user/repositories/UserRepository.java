package com.api.itemstore.user.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.itemstore.user.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}