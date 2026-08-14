package com.inventory.demo.movement;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository <Movement, UUID>{
    List<Movement> findByIngredientId(UUID ingredientId);
}
