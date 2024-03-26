package com.example.notetaking.exception.code;

import com.example.notetaking.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum NoteExceptionCode implements ErrorCode {
    EX_NOTE_NOT_FOUND("note not found", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus status;

    NoteExceptionCode(String message, HttpStatus status){
        this.status = status;
        this.message = message;
    }

    @Override
    public String code() {
        return this.name();
    }


    @Override
    public HttpStatus getStatus() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
