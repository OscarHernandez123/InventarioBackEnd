package com.inventory.demo.movement;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.inventory.demo.enums.MovementReason;
import com.inventory.demo.enums.MovementType;

import jakarta.validation.constraints.NotNull;

public class MovementDtos {

    public record CreateMovementRequest(
        @NotNull MovementType type,
        @NotNull BigDecimal quantity,
        @NotNull MovementReason reason,
        @NotNull UUID ingredientId
    ){}

    public record MovementResponse(
        UUID id,
        MovementType type,
        BigDecimal quantity,
        LocalDateTime date,
        MovementReason reason,
        UUID ingredientId
    ){}
}
