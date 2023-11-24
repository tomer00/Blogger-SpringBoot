package com.tomer.blogger.payloads;

public record AuthResponse(String token, Integer code) {

    //101 - password Error
    //102 - User not found
    //104 - Token Expired
    //200 - Logged in
}
