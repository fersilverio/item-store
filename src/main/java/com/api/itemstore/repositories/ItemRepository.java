package com.api.itemstore.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.itemstore.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

}