package edu.nc.servicebus.model.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestUsernamePasswordFilter extends UsernamePasswordAuthenticationFilter{

    private static final String ERROR = "Error in parser!";

    private final ObjectMapper objMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException{

        String requestBody;
        try{
            requestBody = IOUtils.toString(request.getReader());
            LoginRequest authRequest = objMapper.readValue(requestBody, LoginRequest.class);

            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());

            setDetails(request, token);

            return this.getAuthenticationManager().authenticate(token);
        } catch (IOException e){
            throw new InternalAuthenticationServiceException(ERROR, e);
        }
    }
}
