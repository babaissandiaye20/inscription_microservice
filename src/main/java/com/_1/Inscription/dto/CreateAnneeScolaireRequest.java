package com._1.Inscription.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAnneeScolaireRequest {
    @NotBlank(message = "Le libellé est obligatoire")
    private String libelle;
    private boolean encours;
} 