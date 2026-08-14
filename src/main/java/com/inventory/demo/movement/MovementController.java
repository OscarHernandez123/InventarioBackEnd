package com.inventory.demo.movement;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.inventory.demo.movement.MovementDtos.CreateMovementRequest;
import com.inventory.demo.movement.MovementDtos.MovementResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movements")
@RequiredArgsConstructor
@Validated
public class MovementController {

    private final MovementService movementService;

    @PostMapping
    public ResponseEntity<MovementResponse> createMovement(@Valid @RequestBody CreateMovementRequest request){
        
        MovementResponse movementCreated = movementService.create(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path(("/{id}"))
            .buildAndExpand(movementCreated.id())
            .toUri();

        return ResponseEntity.created(location).body(movementCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementResponse> getMovement(@PathVariable("id") UUID movementId){
        return ResponseEntity.ok(movementService.get(movementId));
    }

    @GetMapping
    public ResponseEntity<List<MovementResponse>> listAll(){
        return ResponseEntity.ok(movementService.listAll());
    }

    @GetMapping("/ingredient/{ingredientId}")
    public ResponseEntity<List<MovementResponse>> getMovementsByIngredient(
        @PathVariable("ingredientId") UUID ingredientId
    ){
        return ResponseEntity.ok(
            movementService.getMovementsByIngredient(ingredientId)
        );
    }
}
