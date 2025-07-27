package com._1.Inscription.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateClasseResponse {
    private long id;
    private String code;
    private String libelle;
    private int fraisInscription;
    private int mensualite;
    private int autreFrais;
    private int nombrePlace;
    private FiliereDto filiere;
} 