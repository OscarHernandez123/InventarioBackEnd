package com.inventory.demo.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inventory.demo.category.CategoryDtos.CategoryResponse;
import com.inventory.demo.category.CategoryDtos.CreateCategoryRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse create(CreateCategoryRequest request) {
        
        Category category = CategoryMapper.toEntity(request);

        Category saved = categoryRepository.save(category);

        return CategoryMapper.toResponse(saved);
    }

    @Override
    public List<CategoryResponse> listAll() {
        
        return categoryRepository.findAll()
            .stream()
            .map(CategoryMapper::toResponse)
            .toList();
    }

}
