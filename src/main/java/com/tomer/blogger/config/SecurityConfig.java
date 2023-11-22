package com.tomer.blogger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public BCryptPasswordEncoder provideBcrypt(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JWTEntryPoint authEntry;
//    @Autowired
//    private JWTFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> {
//            auth.requestMatchers("/user/**").authenticated();
//            auth.requestMatchers("/user").permitAll();
//            auth.requestMatchers("/user/auth").permitAll();
//            auth.requestMatchers("/post/all").hasAuthority("ADMIN");
            auth.anyRequest().permitAll();
        });


//        http.exceptionHandling(ex-> ex.authenticationEntryPoint(authEntry))
//                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public AuthenticationManager authorizationManager(AuthenticationConfiguration man) throws Exception {
        return man.getAuthenticationManager();
    }
}
