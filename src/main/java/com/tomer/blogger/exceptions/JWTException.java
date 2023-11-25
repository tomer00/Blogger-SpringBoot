package com.tomer.blogger.exceptions;

import lombok.Getter;

@Getter
public class JWTException extends RuntimeException {


    //101 -> malformed jwt
    //102 -> expired token
    //103 ->

    private final String message;
    private final int code;

    public JWTException(String message,int code) {
        super(message);
        this.message = message;
        this.code = code;
    }
}
