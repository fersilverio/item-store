package com.api.itemstore.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserDto(@NotBlank String name, @NotBlank String email, @NotBlank String nickName,
        @NotBlank String password) {

}