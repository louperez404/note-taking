package com.example.notetaking.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseException extends RuntimeException {

    private final String code;
    private final String message;
    private final HttpStatus status;

    public BaseException(String message) {
        super(message);
        final var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.code = httpStatus.name();
        this.message = message;
        this.status = httpStatus;
    }

    public BaseException(String code, String message, HttpStatus status) {
        super(message);
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public BaseException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.code = errorCode.code();
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
    }

}
