package com.example.notetaking.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String code();

    HttpStatus getStatus();

    String getMessage();
}
