package com.parc_informatique.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ParcUtils {
    private ParcUtils() {

    }
    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}", httpStatus);

    }
}
