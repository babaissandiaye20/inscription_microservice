package com._1.Inscription.dto;

import com._1.Inscription.validation.ValidInscriptionWithEtudiant;
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
@ValidInscriptionWithEtudiant
public class CreateInscriptionWithEtudiantRequest {
    @NotNull(message = "La date d'inscription est obligatoire")
    private LocalDate date;
    
    @NotNull(message = "L'identifiant de la classe est obligatoire")
    private Long classeId;
    
    @NotNull(message = "L'année scolaire est obligatoire")
    private Long anneeScolaireId;
    
    // Informations de l'étudiant (optionnel si etudiantId est fourni)
    private Long etudiantId;
    
    // Utilise directement CreateEtudiantRequest au lieu de dupliquer les champs
    private CreateEtudiantRequest etudiant;
} 