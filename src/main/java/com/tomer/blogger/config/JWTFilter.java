package com.tomer.blogger.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class JWTFilter extends OncePerRequestFilter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTHelper jwtHelper;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        //getToken
        var token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer")) {

            var jwtToken = token.substring(7);
            String userName = null;

            try {

                userName = jwtHelper.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (ExpiredJwtException e) {
                System.out.println(e.getMessage());
            } catch (MalformedJwtException e) {
                System.out.println(e.getMessage());
            }

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                var userDetail = userDetailsService.loadUserByUsername(userName);

                if (this.jwtHelper.validateToken(token, userDetail)) {

                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(
                                    userDetail, null,
                                    userDetail.getAuthorities())
                    );
                } else System.out.println("Invalid JWT Token");
            }
            filterChain.doFilter(request,response);
            return;
        }

        //error no token
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);

    }
}
