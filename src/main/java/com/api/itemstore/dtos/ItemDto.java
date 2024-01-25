package com.api.itemstore.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemDto(
        @NotBlank String name,
        @NotBlank String description,
        @NotNull BigDecimal price,
        String imagePath) {
}