package com.tomer.blogger.payloads;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthResponse {

    private final String token,message;
    private final Integer code;
}
