package com._1.Inscription.controller;

import com._1.Inscription.dto.CreateAnneeScolaireRequest;
import com._1.Inscription.dto.CreateAnneeScolaireResponse;
import com._1.Inscription.helper.AnneeScolaireHelper;
import com._1.Inscription.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/anneescolaires")
public class AnneeScolaireController {
    private final AnneeScolaireHelper anneeScolaireHelper;

    public AnneeScolaireController(AnneeScolaireHelper anneeScolaireHelper) {
        this.anneeScolaireHelper = anneeScolaireHelper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateAnneeScolaireResponse>> save(@RequestBody @Valid CreateAnneeScolaireRequest request) {
        ApiResponse<CreateAnneeScolaireResponse> response = anneeScolaireHelper.save(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CreateAnneeScolaireResponse>>> allAnnees() {
        ApiResponse<List<CreateAnneeScolaireResponse>> response = anneeScolaireHelper.findAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CreateAnneeScolaireResponse>> getById(@PathVariable long id) {
        ApiResponse<CreateAnneeScolaireResponse> response = anneeScolaireHelper.findById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CreateAnneeScolaireResponse>> update(@PathVariable long id, @RequestBody @Valid CreateAnneeScolaireRequest request) {
        ApiResponse<CreateAnneeScolaireResponse> response = anneeScolaireHelper.update(id, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable long id) {
        ApiResponse<String> response = anneeScolaireHelper.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
} 