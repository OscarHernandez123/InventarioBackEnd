package com.inventory.demo.ingredient;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.inventory.demo.ingredient.IngredientDtos.CreateIngredientRequest;
import com.inventory.demo.ingredient.IngredientDtos.IngredientResponse;
import com.inventory.demo.ingredient.IngredientDtos.PatchIngredientRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
@Validated
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<IngredientResponse> createIngredient(@Valid @RequestBody CreateIngredientRequest request){

        IngredientResponse ingredientCreated = ingredientService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("{id}")
            .buildAndExpand(ingredientCreated.id())
            .toUri();

        return ResponseEntity.created(location).body(ingredientCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientResponse> getIngredient(@PathVariable("id") UUID ingredientId){
        return ResponseEntity.ok(ingredientService.get(ingredientId));
    }

    @GetMapping
    public ResponseEntity<List<IngredientResponse>> listAll(){
        return ResponseEntity.ok(ingredientService.listAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<IngredientResponse> patchIngredient(
            @PathVariable("id") UUID ingredientId,
            @Valid @RequestBody PatchIngredientRequest request
    ){
        IngredientResponse response = ingredientService.patch(request, ingredientId);
        return ResponseEntity.ok(response);
    }
}
