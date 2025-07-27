package com._1.Inscription.validation;

import com._1.Inscription.dto.CreateInscriptionWithEtudiantRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class InscriptionWithEtudiantValidator implements ConstraintValidator<ValidInscriptionWithEtudiant, CreateInscriptionWithEtudiantRequest> {

    @Override
    public boolean isValid(CreateInscriptionWithEtudiantRequest request, ConstraintValidatorContext context) {
        // Si etudiantId est fourni, les autres champs d'étudiant ne sont pas obligatoires
        if (request.getEtudiantId() != null) {
            return true;
        }
        
        // Si etudiantId n'est pas fourni, l'objet etudiant doit être fourni avec toutes ses informations
        if (request.getEtudiant() == null) {
            return false;
        }
        
        return StringUtils.hasText(request.getEtudiant().getMatricule()) &&
               StringUtils.hasText(request.getEtudiant().getNom()) &&
               StringUtils.hasText(request.getEtudiant().getPrenom()) &&
               StringUtils.hasText(request.getEtudiant().getEmail()) &&
               StringUtils.hasText(request.getEtudiant().getTelephone()) &&
               StringUtils.hasText(request.getEtudiant().getAdresse()) &&
               request.getEtudiant().getDateNaissance() != null &&
               StringUtils.hasText(request.getEtudiant().getLieuNaissance());
    }
} 