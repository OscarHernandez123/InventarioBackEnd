package com.inventory.demo.category;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public class CategoryDtos {

    public record CreateCategoryRequest(
        @NotBlank String name,
        @NotBlank String description
    ){}

    public record CategoryResponse(
        UUID id,
        String name,
        String description
    ){}
}
