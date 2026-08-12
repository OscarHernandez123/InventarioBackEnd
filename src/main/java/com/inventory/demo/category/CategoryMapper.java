package com.inventory.demo.category;

import com.inventory.demo.category.CategoryDtos.CategoryResponse;
import com.inventory.demo.category.CategoryDtos.CreateCategoryRequest;

public class CategoryMapper {

    public static Category toEntity(CreateCategoryRequest request){
        return Category.builder()
                .name(request.name())
                .description(request.description())
                .build();
    }

    public static CategoryResponse toResponse(Category category){
        return new CategoryResponse(
            category.getId(),
            category.getName(),
            category.getDescription()
        );
    }
}
