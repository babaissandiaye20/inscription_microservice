package com._1.Inscription.helper;

import com._1.Inscription.dto.CreateAnneeScolaireRequest;
import com._1.Inscription.dto.CreateAnneeScolaireResponse;
import com._1.Inscription.exception.NotFoundException;
import com._1.Inscription.mapper.AnneeScolaireMapper;
import com._1.Inscription.model.AnneeScolaire;
import com._1.Inscription.response.ApiResponse;
import com._1.Inscription.service.IAnneeScolaireService;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnneeScolaireHelper {
    private final IAnneeScolaireService anneeScolaireService;

    public AnneeScolaireHelper(IAnneeScolaireService anneeScolaireService) {
        this.anneeScolaireService = anneeScolaireService;
    }

    public ApiResponse<CreateAnneeScolaireResponse> save(CreateAnneeScolaireRequest request) {
        AnneeScolaire annee = AnneeScolaireMapper.toEntity(request);
        annee = anneeScolaireService.save(annee);
        CreateAnneeScolaireResponse response = AnneeScolaireMapper.toResponse(annee);
        return ApiResponse.<CreateAnneeScolaireResponse>builder()
                .message("Année scolaire créée avec succès")
                .date(LocalDateTime.now())
                .status(201)
                .data(response)
                .build();
    }

    public ApiResponse<CreateAnneeScolaireResponse> findById(long id) {
        AnneeScolaire annee = anneeScolaireService.findById(id);
        if (annee == null) {
            throw new NotFoundException("Année scolaire avec id " + id + " introuvable");
        }
        CreateAnneeScolaireResponse response = AnneeScolaireMapper.toResponse(annee);
        return ApiResponse.<CreateAnneeScolaireResponse>builder()
                .message("Année scolaire trouvée")
                .date(LocalDateTime.now())
                .status(200)
                .data(response)
                .build();
    }

    public ApiResponse<List<CreateAnneeScolaireResponse>> findAll() {
        List<CreateAnneeScolaireResponse> list = anneeScolaireService.findAll().stream()
                .map(AnneeScolaireMapper::toResponse)
                .collect(Collectors.toList());
        return ApiResponse.<List<CreateAnneeScolaireResponse>>builder()
                .message("Liste des années scolaires récupérée avec succès")
                .date(LocalDateTime.now())
                .status(200)
                .data(list)
                .build();
    }

    public ApiResponse<CreateAnneeScolaireResponse> update(long id, CreateAnneeScolaireRequest request) {
        AnneeScolaire existing = anneeScolaireService.findById(id);
        if (existing == null) {
            throw new NotFoundException("Année scolaire avec id " + id + " introuvable");
        }
        AnneeScolaire updated = AnneeScolaireMapper.toEntity(request);
        updated.setId(id);
        updated = anneeScolaireService.update(id, updated);
        CreateAnneeScolaireResponse response = AnneeScolaireMapper.toResponse(updated);
        return ApiResponse.<CreateAnneeScolaireResponse>builder()
                .message("Année scolaire mise à jour avec succès")
                .date(LocalDateTime.now())
                .status(200)
                .data(response)
                .build();
    }

    public ApiResponse<String> delete(long id) {
        AnneeScolaire annee = anneeScolaireService.findById(id);
        if (annee == null) {
            throw new NotFoundException("Année scolaire avec id " + id + " introuvable");
        }
        anneeScolaireService.delete(annee);
        return ApiResponse.<String>builder()
                .message("Année scolaire supprimée avec succès")
                .date(LocalDateTime.now())
                .status(200)
                .data("Année scolaire supprimée")
                .build();
    }
} 