package com._1.Inscription.exception;

import com._1.Inscription.dto.ErrorDto;
import org.springframework.http.HttpStatus;

public class ErrorInternetException extends RuntimeException {
    private final ErrorDto errorDto;
    private final HttpStatus status;

    public ErrorInternetException(String message) {
        super(message);
        this.status = HttpStatus.SERVICE_UNAVAILABLE;
        this.errorDto = new ErrorDto("ERROR_INTERNET", message);
    }

    public ErrorDto getErrorDto() {
        return errorDto;
    }

    public HttpStatus getStatus() {
        return status;
    }
} 