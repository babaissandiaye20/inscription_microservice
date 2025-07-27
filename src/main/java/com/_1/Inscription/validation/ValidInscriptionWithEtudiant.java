package com._1.Inscription.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = InscriptionWithEtudiantValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidInscriptionWithEtudiant {
    String message() default "Soit l'ID de l'étudiant soit toutes les informations de l'étudiant doivent être fournies";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
} 