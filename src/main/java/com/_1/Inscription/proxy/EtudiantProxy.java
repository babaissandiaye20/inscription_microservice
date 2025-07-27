package com._1.Inscription.proxy;

import com._1.Inscription.dto.CreateEtudiantRequest;
import com._1.Inscription.dto.CreateEtudiantResponse;
import com._1.Inscription.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", url = "${services.gestion-etudiant.url}")
public interface EtudiantProxy {
    @PostMapping("/api/etudiants")
    ApiResponse<CreateEtudiantResponse> save(@RequestBody CreateEtudiantRequest etudiantRequest);

    @GetMapping("/api/etudiants/{id}")
    ApiResponse<CreateEtudiantResponse> findById(@PathVariable("id") long id);
} 