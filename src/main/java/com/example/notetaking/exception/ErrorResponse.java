package com.example.notetaking.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(String message, String code) {

}
