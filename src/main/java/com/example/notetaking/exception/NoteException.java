package com.example.notetaking.exception;

import org.springframework.http.HttpStatus;

public class NoteException extends BaseException {
    public NoteException(String message) {
        super(message);
    }

    public NoteException(String code, String message, HttpStatus status) {
        super(code, message, status);
    }

    public NoteException(ErrorCode errorCode) {
        super(errorCode);
    }
}
