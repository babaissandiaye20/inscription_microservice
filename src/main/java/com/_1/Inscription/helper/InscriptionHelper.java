package com._1.Inscription.helper;

import com._1.Inscription.dto.CreateInscriptionRequest;
import com._1.Inscription.dto.CreateInscriptionResponse;
import com._1.Inscription.dto.CreateInscriptionWithEtudiantRequest;
import com._1.Inscription.dto.CreateEtudiantRequest;
import com._1.Inscription.dto.CreateEtudiantResponse;
import com._1.Inscription.model.AnneeScolaire;
import com._1.Inscription.model.Inscription;
import com._1.Inscription.proxy.ClasseProxy;
import com._1.Inscription.proxy.EtudiantProxy;
import com._1.Inscription.response.ApiResponse;
import com._1.Inscription.dto.CreateClasseResponse;
import com._1.Inscription.exception.NotFoundException;
import com._1.Inscription.exception.AlreadyExistsException;
import com._1.Inscription.mapper.InscriptionMapper;
import com._1.Inscription.repository.AnneeScolaireRepository;
import com._1.Inscription.service.IInscriptionService;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InscriptionHelper {
    private final IInscriptionService inscriptionService;
    private final ClasseProxy classeProxy;
    private final EtudiantProxy etudiantProxy;
    private final AnneeScolaireRepository anneeScolaireRepository;

    public InscriptionHelper(IInscriptionService inscriptionService, ClasseProxy classeProxy, EtudiantProxy etudiantProxy, AnneeScolaireRepository anneeScolaireRepository) {
        this.inscriptionService = inscriptionService;
        this.classeProxy = classeProxy;
        this.etudiantProxy = etudiantProxy;
        this.anneeScolaireRepository = anneeScolaireRepository;
    }

    public ApiResponse<CreateInscriptionResponse> save(CreateInscriptionRequest request) {
        // Vérification existence Etudiant
        var etudiantResponse = etudiantProxy.findById(request.getEtudiantId());
        if (etudiantResponse.getData() == null) {
            throw new NotFoundException("Etudiant avec id " + request.getEtudiantId() + " introuvable");
        }
        // Vérification existence Classe
        var classeResponse = classeProxy.findClasseById(request.getClasseId());
        if (classeResponse == null) {
            throw new NotFoundException("Classe avec id " + request.getClasseId() + " introuvable");
        }
        // Vérification existence Année Scolaire
        AnneeScolaire annee = anneeScolaireRepository.findById(request.getAnneeScolaireId()).orElse(null);
        if (annee == null) {
            throw new NotFoundException("Année scolaire avec id " + request.getAnneeScolaireId() + " introuvable");
        }
        
        // Vérification de doublon d'inscription
        var existingInscription = inscriptionService.findByEtudiantIdAndAnneeScolaire(request.getEtudiantId(), annee);
        if (existingInscription.isPresent()) {
            throw new AlreadyExistsException("L'étudiant avec l'ID " + request.getEtudiantId() + " est déjà inscrit pour l'année scolaire " + annee.getLibelle());
        }
        
        // Mapping et sauvegarde
        Inscription inscription = InscriptionMapper.toEntity(request, annee);
        inscription = inscriptionService.save(inscription);
        CreateInscriptionResponse response = InscriptionMapper.toResponse(inscription);
        return ApiResponse.<CreateInscriptionResponse>builder()
                .message("Inscription créée avec succès")
                .date(LocalDateTime.now())
                .status(201)
                .data(response)
                .build();
    }
    
    public ApiResponse<CreateInscriptionResponse> saveWithEtudiant(CreateInscriptionWithEtudiantRequest request) {
        // Vérification existence Classe
        var classeResponse = classeProxy.findClasseById(request.getClasseId());
        if (classeResponse == null) {
            throw new NotFoundException("Classe avec id " + request.getClasseId() + " introuvable");
        }
        
        // Vérification existence Année Scolaire
        AnneeScolaire annee = anneeScolaireRepository.findById(request.getAnneeScolaireId()).orElse(null);
        if (annee == null) {
            throw new NotFoundException("Année scolaire avec id " + request.getAnneeScolaireId() + " introuvable");
        }
        
        Long etudiantId = request.getEtudiantId();
        
        // Mode 1: Si etudiantId est fourni, vérifier s'il existe
        if (etudiantId != null) {
            var etudiantResponse = etudiantProxy.findById(etudiantId);
            if (etudiantResponse.getData() == null) {
                throw new NotFoundException("Etudiant avec id " + etudiantId + " introuvable");
            }
        } else {
            // Mode 2: Créer l'étudiant avec les informations fournies
            CreateEtudiantRequest etudiantRequest = InscriptionMapper.toEtudiantRequest(request);
            var etudiantResponse = etudiantProxy.save(etudiantRequest);
            if (etudiantResponse.getData() == null) {
                throw new NotFoundException("Impossible de créer l'étudiant");
            }
            etudiantId = etudiantResponse.getData().getId();
        }
        
        // Vérification de doublon d'inscription
        var existingInscription = inscriptionService.findByEtudiantIdAndAnneeScolaire(etudiantId, annee);
        
        // Vérification de doublon d'inscription
    
        if (existingInscription.isPresent()) {
            throw new AlreadyExistsException("L'étudiant avec l'ID " + etudiantId + " est déjà inscrit pour l'année scolaire " + annee.getLibelle());
        }
        
        // Mapping et sauvegarde
        Inscription inscription = InscriptionMapper.toEntityFromWithEtudiant(request, annee, etudiantId);
        inscription = inscriptionService.save(inscription);
        CreateInscriptionResponse response = InscriptionMapper.toResponse(inscription);
        return ApiResponse.<CreateInscriptionResponse>builder()
                .message("Inscription créée avec succès")
                .date(LocalDateTime.now())
                .status(201)
                .data(response)
                .build();
    }

    public ApiResponse<CreateInscriptionResponse> findById(long id) {
        Inscription inscription = inscriptionService.findById(id);
        if (inscription == null) {
            throw new NotFoundException("Inscription avec id " + id + " introuvable");
        }
        CreateInscriptionResponse response = InscriptionMapper.toResponse(inscription);
        return ApiResponse.<CreateInscriptionResponse>builder()
                .message("Inscription trouvée")
                .date(LocalDateTime.now())
                .status(200)
                .data(response)
                .build();
    }

    public ApiResponse<List<CreateInscriptionResponse>> findAll() {
        List<CreateInscriptionResponse> list = inscriptionService.findAll().stream()
                .map(InscriptionMapper::toResponse)
                .collect(Collectors.toList());
        return ApiResponse.<List<CreateInscriptionResponse>>builder()
                .message("Liste des inscriptions récupérée avec succès")
                .date(LocalDateTime.now())
                .status(200)
                .data(list)
                .build();
    }

    public ApiResponse<CreateInscriptionResponse> update(long id, CreateInscriptionRequest request) {
        // Vérification existence Inscription
        Inscription existing = inscriptionService.findById(id);
        if (existing == null) {
            throw new NotFoundException("Inscription avec id " + id + " introuvable");
        }
        // Vérification existence des IDs liés
        var etudiantResponse = etudiantProxy.findById(request.getEtudiantId());
        if (etudiantResponse.getData() == null) {
            throw new NotFoundException("Etudiant avec id " + request.getEtudiantId() + " introuvable");
        }
        var classeResponse = classeProxy.findClasseById(request.getClasseId());
        if (classeResponse == null) {
            throw new NotFoundException("Classe avec id " + request.getClasseId() + " introuvable");
        }
        AnneeScolaire annee = anneeScolaireRepository.findById(request.getAnneeScolaireId()).orElse(null);
        if (annee == null) {
            throw new NotFoundException("Année scolaire avec id " + request.getAnneeScolaireId() + " introuvable");
        }
        
        // Vérification de doublon d'inscription (exclure l'inscription actuelle)
        var existingInscription = inscriptionService.findByEtudiantIdAndAnneeScolaire(request.getEtudiantId(), annee);
        if (existingInscription.isPresent() && !existingInscription.get().getId().equals(id)) {
            throw new AlreadyExistsException("L'étudiant avec l'ID " + request.getEtudiantId() + " est déjà inscrit pour l'année scolaire " + annee.getLibelle());
        }
        
        // Mapping et update
        Inscription updated = InscriptionMapper.toEntity(request, annee);
        updated.setId(id);
        updated = inscriptionService.update(id, updated);
        CreateInscriptionResponse response = InscriptionMapper.toResponse(updated);
        return ApiResponse.<CreateInscriptionResponse>builder()
                .message("Inscription mise à jour avec succès")
                .date(LocalDateTime.now())
                .status(200)
                .data(response)
                .build();
    }

    public ApiResponse<String> delete(long id) {
        Inscription inscription = inscriptionService.findById(id);
        if (inscription == null) {
            throw new NotFoundException("Inscription avec id " + id + " introuvable");
        }
        inscriptionService.delete(inscription);
        return ApiResponse.<String>builder()
                .message("Inscription supprimée avec succès")
                .date(LocalDateTime.now())
                .status(200)
                .data("Inscription supprimée")
                .build();
    }
} 