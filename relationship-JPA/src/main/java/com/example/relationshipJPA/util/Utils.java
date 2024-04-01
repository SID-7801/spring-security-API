package com.example.relationshipJPA.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Utils {
    private Utils() {
    }

    public static ResponseEntity<String> getResponseEntity(String message, HttpStatus status) {
        return new ResponseEntity<>("{\"message\" : " + "\"" + message + "\"" + "}", status);
    }
}
