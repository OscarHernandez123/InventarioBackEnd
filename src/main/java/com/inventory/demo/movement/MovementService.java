package com.inventory.demo.movement;

import java.util.List;
import java.util.UUID;

import com.inventory.demo.movement.MovementDtos.CreateMovementRequest;
import com.inventory.demo.movement.MovementDtos.MovementResponse;

public interface MovementService {
    MovementResponse create(CreateMovementRequest request);
    List<MovementResponse> getMovementsByIngredient(UUID ingredientId);
    MovementResponse get(UUID movementId);
    List<MovementResponse> listAll();
}
