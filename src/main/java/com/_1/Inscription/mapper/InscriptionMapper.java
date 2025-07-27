package com._1.Inscription.mapper;

import com._1.Inscription.model.Inscription;
import com._1.Inscription.model.AnneeScolaire;
import com._1.Inscription.dto.CreateInscriptionRequest;
import com._1.Inscription.dto.CreateInscriptionResponse;
import com._1.Inscription.dto.CreateInscriptionWithEtudiantRequest;
import com._1.Inscription.dto.CreateEtudiantRequest;

public class InscriptionMapper {
    public static Inscription toEntity(CreateInscriptionRequest request, AnneeScolaire anneeScolaire) {
        Inscription inscription = new Inscription();
        inscription.setDate(request.getDate());
        inscription.setClasseId(request.getClasseId());
        inscription.setEtudiantId(request.getEtudiantId());
        inscription.setAnneeScolaire(anneeScolaire);
        return inscription;
    }

    public static CreateInscriptionResponse toResponse(Inscription entity) {
        return CreateInscriptionResponse.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .classeId(entity.getClasseId())
                .etudiantId(entity.getEtudiantId())
                .anneeScolaireId(entity.getAnneeScolaire() != null ? entity.getAnneeScolaire().getId() : null)
                .build();
    }
    
    public static CreateEtudiantRequest toEtudiantRequest(CreateInscriptionWithEtudiantRequest request) {
        return request.getEtudiant();
    }
    
    public static Inscription toEntityFromWithEtudiant(CreateInscriptionWithEtudiantRequest request, AnneeScolaire anneeScolaire, Long etudiantId) {
        Inscription inscription = new Inscription();
        inscription.setDate(request.getDate());
        inscription.setClasseId(request.getClasseId());
        inscription.setEtudiantId(etudiantId);
        inscription.setAnneeScolaire(anneeScolaire);
        return inscription;
    }
} 