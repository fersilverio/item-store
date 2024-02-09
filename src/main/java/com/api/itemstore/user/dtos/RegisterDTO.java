package com.api.itemstore.user.dtos;

import com.api.itemstore.user.enums.UserRole;

public record RegisterDTO(String email, String password, UserRole role) {
}