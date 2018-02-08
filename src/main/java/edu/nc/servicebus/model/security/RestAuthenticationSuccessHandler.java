package edu.nc.servicebus.model.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

    @Value("${jwt.expires_in}")
    private int EXPIRES_IN;

    @Value("${jwt.cookie")
    private String TOKEN_COOKIE;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        clearAuthenticationAttributes(request);

        User user = (User) authentication.getPrincipal();

        String jws = tokenUtil.generateToken(user);

        Cookie authCookie = new Cookie(TOKEN_COOKIE, jws);
        authCookie.setHttpOnly(true);
        authCookie.setMaxAge(EXPIRES_IN);
        authCookie.setPath("/user");

        response.addCookie(authCookie);

        ResponseToken responseToken = new ResponseToken(jws);
        String jwtResponse = objectMapper.writeValueAsString(responseToken);
        response.setContentType("application/json");
        response.getWriter().write(jwtResponse);
    }
}
