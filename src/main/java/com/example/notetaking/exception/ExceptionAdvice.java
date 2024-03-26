package com.example.notetaking.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest req, Exception e) {
        final var status = INTERNAL_SERVER_ERROR;
        log.error(e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(e.getMessage())
                .code(status.name())
                .build(), status);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest req, BaseException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(e.getMessage())
                .code(e.getCode())
                .build(), e.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest req, ConstraintViolationException e) {
        final var violations = e.getConstraintViolations();

        var errorMessage = new StringBuilder();
        for (final var violation : violations) {
            errorMessage.append(violation.getPropertyPath()).append(": ").append(violation.getMessage()).append("; ");
        }

        final var errorCode = "CONSTRAINT_VIOLATION_ERROR";
        final var errorDescription = errorMessage.toString();
        log.error(errorDescription);
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(errorDescription)
                .code(errorCode)
                .build(), BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest req, MethodArgumentNotValidException e) {
        final var fieldError = e.getBindingResult().getFieldError();
        final var fieldName = fieldError.getField();
        final var errorMessage = fieldError.getDefaultMessage();

        final var codeName = "VALIDATION_ERROR";
        final var msg = String.format("Validation failed for field '%s': %s", fieldName, errorMessage);
        log.error(msg);
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(msg)
                .code(codeName)
                .build(), BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest req, HttpRequestMethodNotSupportedException e) {
        final var status = BAD_REQUEST;
        final var code = status.name();
        log.error(e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(e.getMessage())
                .code(code)
                .build(), status);
    }

}
