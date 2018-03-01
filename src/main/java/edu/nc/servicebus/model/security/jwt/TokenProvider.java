package edu.nc.servicebus.model.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class TokenProvider {

    @Value("${jwt.secret_key}")
    private String secretKey;

    @Value("${jwt.token_validity}")
    private long tokenValidity;

    private UserDetailsService userService;

    public TokenProvider(UserDetailsService userService){
        this.userService = userService;
    }

    public String createToken(String username){
        Date currentDate = new Date();
        Date validity = new Date(currentDate.getTime() + (this.tokenValidity * 1000));

        return Jwts.builder().setId(UUID.randomUUID().toString()).setSubject(username)
                .setIssuedAt(currentDate).signWith(SignatureAlgorithm.HS512, this.secretKey)
                .setExpiration(validity).compact();
    }

    public Authentication getAuthentication(String token){
        String username = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token)
                .getBody().getSubject();
        UserDetails userDetails = this.userService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Boolean validateToken(String token){
        if (token != null){
            Claims claim = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
            Date expiration = claim.getExpiration();
            Date currentDate = new Date();
            if (expiration.getTime() > currentDate.getTime()){
                return true;
            }
        }
        return false;
    }
}
