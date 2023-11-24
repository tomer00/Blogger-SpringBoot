package com.tomer.blogger.controllers;

import com.tomer.blogger.config.JWTHelper;
import com.tomer.blogger.payloads.AuthLoad;
import com.tomer.blogger.payloads.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationManager manager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JWTHelper helper;

    @PostMapping
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthLoad authLoad) {
        return ResponseEntity.ok(authenticate(authLoad.getUser(), authLoad.getPass()));
    }

    private AuthResponse authenticate(String user, String pass) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, pass);
        try {

            manager.authenticate(token);
            var details = userDetailsService.loadUserByUsername(user);

            var t = helper.generateToken(details);
            return new AuthResponse(t, 200);
        } catch (BadCredentialsException e) {
            return new AuthResponse("Password Error!!", 101);
        } catch (InternalAuthenticationServiceException e) {
            return new AuthResponse("User not found", 102);
        }

    }
}
