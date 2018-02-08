package edu.nc.servicebus.controller;

import edu.nc.servicebus.model.security.LoginRequest;
import edu.nc.servicebus.model.security.ResponseToken;
import edu.nc.servicebus.model.security.TokenUtil;
import edu.nc.servicebus.model.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthenticationController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenUtil tokenUtil;

    @Bean
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity signIn(@RequestBody LoginRequest userData){
        String username = userData.getUsername();
        String password = userData.getPassword();

        User clientUser;

        clientUser = (User) userDetailsService.loadUserByUsername(username);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return ResponseEntity.ok(new ResponseToken(tokenUtil.generateToken(clientUser)));
    }
}
