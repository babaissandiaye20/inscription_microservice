package com._1.Inscription.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateInscriptionRequest {
    @NotNull(message = "La date d'inscription est obligatoire")
    private LocalDate date;
    @NotNull(message = "L'identifiant de la classe est obligatoire")
    private Long classeId;
    @NotNull(message = "L'identifiant de l'étudiant est obligatoire")
    private Long etudiantId;
    @NotNull(message = "L'année scolaire est obligatoire")
    private Long anneeScolaireId;
} 