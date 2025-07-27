package com._1.Inscription.controller;

import com._1.Inscription.dto.CreateInscriptionRequest;
import com._1.Inscription.dto.CreateInscriptionResponse;
import com._1.Inscription.dto.CreateInscriptionWithEtudiantRequest;
import com._1.Inscription.helper.InscriptionHelper;
import com._1.Inscription.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/inscriptions")
public class InscriptionController {
    private final InscriptionHelper inscriptionHelper;

    public InscriptionController(InscriptionHelper inscriptionHelper) {
        this.inscriptionHelper = inscriptionHelper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateInscriptionResponse>> save(@RequestBody @Valid CreateInscriptionRequest request) {
        ApiResponse<CreateInscriptionResponse> response = inscriptionHelper.save(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
    @PostMapping("/with-etudiant")
    public ResponseEntity<ApiResponse<CreateInscriptionResponse>> saveWithEtudiant(@RequestBody @Valid CreateInscriptionWithEtudiantRequest request) {
        ApiResponse<CreateInscriptionResponse> response = inscriptionHelper.saveWithEtudiant(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CreateInscriptionResponse>>> allInscriptions() {
        ApiResponse<List<CreateInscriptionResponse>> response = inscriptionHelper.findAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CreateInscriptionResponse>> getById(@PathVariable long id) {
        ApiResponse<CreateInscriptionResponse> response = inscriptionHelper.findById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CreateInscriptionResponse>> update(@PathVariable long id, @RequestBody @Valid CreateInscriptionRequest request) {
        ApiResponse<CreateInscriptionResponse> response = inscriptionHelper.update(id, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
} 