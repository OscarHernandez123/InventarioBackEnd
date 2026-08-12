package com.inventory.demo.ingredient;

import java.util.List;
import java.util.UUID;

import com.inventory.demo.ingredient.IngredientDtos.CreateIngredientRequest;
import com.inventory.demo.ingredient.IngredientDtos.IngredientResponse;

public interface IngredientService {
    IngredientResponse create(CreateIngredientRequest request);
    IngredientResponse get(UUID ingredientId);
    List<IngredientResponse> listAll();
}
