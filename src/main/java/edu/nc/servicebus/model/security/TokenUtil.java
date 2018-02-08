package edu.nc.servicebus.model.security;


import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername());
        claims.put("role", "ADMIN");
        claims.put("created", new Date());
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .compact();
    }
}
