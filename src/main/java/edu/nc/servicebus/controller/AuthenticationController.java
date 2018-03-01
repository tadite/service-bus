package edu.nc.servicebus.controller;

import edu.nc.servicebus.datagrid.model.User;
import edu.nc.servicebus.model.security.*;
import edu.nc.servicebus.model.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private Registration registration;

    @Bean
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @PostMapping("/user")
    public ResponseEntity signIn(@RequestBody LoginRequest userData,
                                 HttpServletResponse response){
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        userData.getUsername(), userData.getPassword()
                );

        try{
            this.authenticationManager.authenticate(token);
            String authToken = this.tokenProvider.createToken(userData.getUsername());
            return ResponseEntity.ok(authToken);   //new TokenResponse(authToken));
        } catch (AuthenticationException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }

    @PostMapping("/register")
    public ResponseEntity signUp(@RequestBody User user,
                                 HttpServletResponse response){

        if (!registration.checkUsername(user.getLogin())){
            return ResponseEntity.ok("NAME_EXIST");
        }
        if (!registration.checkEmail(user.getEmail())){
            return ResponseEntity.ok("EMAIL_EXIST");
        }

        registration.addUser(user);
        return ResponseEntity.ok("ADDED");
    }
}
