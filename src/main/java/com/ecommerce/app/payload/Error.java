package com.ecommerce.app.payload;

import org.springframework.http.HttpStatus;

import java.util.List;

public record Error(
        List<String> errors,
        HttpStatus code
) {

    public Error(String errorMessage, HttpStatus code) {
        this(List.of(errorMessage), code);
    }

}
