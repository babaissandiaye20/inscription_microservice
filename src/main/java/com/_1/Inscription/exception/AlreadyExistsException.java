package com._1.Inscription.exception;

import com._1.Inscription.dto.ErrorDto;
import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends RuntimeException {
    private final ErrorDto errorDto;
    private final HttpStatus status;

    public AlreadyExistsException(String message) {
        super(message);
        this.status = HttpStatus.CONFLICT;
        this.errorDto = new ErrorDto("ALREADY_EXISTS", message);
    }

    public ErrorDto getErrorDto() {
        return errorDto;
    }

    public HttpStatus getStatus() {
        return status;
    }
} 