package com._1.Inscription.exception;

import com._1.Inscription.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleAlreadyExists(AlreadyExistsException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getErrorDto());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getErrorDto());
    }

    @ExceptionHandler(ErrorInternetException.class)
    public ResponseEntity<ErrorDto> handleInternet(ErrorInternetException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getErrorDto());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> Map.of("field", err.getField(), "message", err.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(Map.of(
                "code", HttpStatus.BAD_REQUEST.value(),
                "status", HttpStatus.BAD_REQUEST.name(),
                "message", "Erreur de validation",
                "errors", errors
        ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleInvalidJson(HttpMessageNotReadableException ex) {
        String message = ex.getMostSpecificCause().getMessage();
        return ResponseEntity.badRequest().body(Map.of(
                "code", HttpStatus.BAD_REQUEST.value(),
                "status", HttpStatus.BAD_REQUEST.name(),
                "message", "Format JSON invalide ou valeur incorrecte (type ou taille)",
                "details", message
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleOtherErrors(Exception ex) {
        ErrorDto error = new ErrorDto("INTERNAL_ERROR", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
} 