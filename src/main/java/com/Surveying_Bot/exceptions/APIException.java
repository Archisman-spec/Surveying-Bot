package com.Surveying_Bot.exceptions;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class APIException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public APIException(String message, Throwable cause){
        super(message,cause);
    }

    public APIException(String message){
        super(message);
    }

}
