package com.inventory.demo.category;

import java.util.List;

import com.inventory.demo.category.CategoryDtos.CategoryResponse;
import com.inventory.demo.category.CategoryDtos.CreateCategoryRequest;

public interface CategoryService {
    CategoryResponse create(CreateCategoryRequest request);
    List<CategoryResponse> listAll();
}
