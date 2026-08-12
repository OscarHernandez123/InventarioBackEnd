package com.inventory.demo.ingredient;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID>{
    
    List<Ingredient> findByCategoryId(UUID categoryId);
}
