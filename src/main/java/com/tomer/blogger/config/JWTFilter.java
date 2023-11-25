package com.tomer.blogger.config;

import com.tomer.blogger.exceptions.JWTException;
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
import java.util.Objects;

@Component
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

        if (Objects.equals(request.getRequestURI(), "/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        //getToken
        var tok = request.getHeader("Authorization");
        if (tok != null && tok.startsWith("Bearer")) {

            var jwtToken = tok.substring(7);
            String userName = null;

            try {
                userName = jwtHelper.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("52  "+e.getMessage());
            } catch (MalformedJwtException e) {
                System.out.println("54  "+e.getMessage());
            } catch (ExpiredJwtException e) {
//                throw  new JWTException("Jwt Token Expired",102);
            }catch (Exception e) {
//                throw  new JWTException("Malformed Token",101);
            }

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                var userDetail = userDetailsService.loadUserByUsername(userName);

                if (this.jwtHelper.validateToken(jwtToken, userDetail)) {

                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(
                                    userDetail, null,
                                    userDetail.getAuthorities())
                    );
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied!!");
                    return;
                }
            }
            filterChain.doFilter(request, response);
            return;
        }

//        error no token
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied!!");

    }
}
