package com.inventory.demo.ingredient;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.inventory.demo.category.Category;
import com.inventory.demo.category.CategoryRepository;
import com.inventory.demo.exceptions.BusinessException;
import com.inventory.demo.exceptions.ResourceNotFoundException;
import com.inventory.demo.ingredient.IngredientDtos.CreateIngredientRequest;
import com.inventory.demo.ingredient.IngredientDtos.IngredientResponse;
import com.inventory.demo.ingredient.IngredientDtos.PatchIngredientRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class IngredientServiceImpl implements IngredientService{

    private final IngredientRepository ingredientRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public IngredientResponse create(CreateIngredientRequest request) {
        
        Category category = categoryRepository.findById(request.categoryId())
            .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

        if(request.quantity().compareTo(BigDecimal.ZERO) <= 0){
            throw new BusinessException("Quantity has to be positive");
        }

        if(request.minimumStock().compareTo(BigDecimal.ZERO) < 0){
            throw new BusinessException("Minimum Stock has to be positive");
        }

        if(request.minimumStock().compareTo(request.quantity()) > 0){
            throw new BusinessException("Quantity has to be equals or greater than minimum stock");
        }

        Ingredient ingredient = IngredientMapper.toEntity(request, category);

        Ingredient saved = ingredientRepository.save(ingredient);

        return IngredientMapper.toResponse(saved);
    }

    @Override
    public IngredientResponse get(UUID ingredientId) {

        Ingredient ingredient = ingredientRepository.findById(ingredientId)
            .orElseThrow(() -> new ResourceNotFoundException("Ingredient Not Found"));

        return IngredientMapper.toResponse(ingredient);
    }

    @Override
    public IngredientResponse patch(PatchIngredientRequest request, UUID ingredientId){

        Category category = categoryRepository.findById(request.categoryId())
            .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

        Ingredient ingredient = ingredientRepository.findById(ingredientId)
            .orElseThrow(() -> new ResourceNotFoundException("Ingredient Not Found"));

        IngredientMapper.patch(request, ingredient, category);
        Ingredient saved = ingredientRepository.save(ingredient);

        return IngredientMapper.toResponse(saved);
    }

    @Override
    public List<IngredientResponse> listAll() {
        
        return ingredientRepository.findAll()
            .stream()
            .map(IngredientMapper::toResponse)
            .toList();
    }

}
