package com._1.Inscription.mapper;

import com._1.Inscription.model.AnneeScolaire;
import com._1.Inscription.dto.CreateAnneeScolaireRequest;
import com._1.Inscription.dto.CreateAnneeScolaireResponse;

public class AnneeScolaireMapper {
    public static AnneeScolaire toEntity(CreateAnneeScolaireRequest request) {
        return AnneeScolaire.builder()
                .libelle(request.getLibelle())
                .encours(request.isEncours())
                .build();
    }

    public static CreateAnneeScolaireResponse toResponse(AnneeScolaire entity) {
        return CreateAnneeScolaireResponse.builder()
                .id(entity.getId())
                .libelle(entity.getLibelle())
                .encours(entity.isEncours())
                .build();
    }
} 