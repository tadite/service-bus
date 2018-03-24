package edu.nc.servicebus.model.security;

import edu.nc.servicebus.model.security.jwt.TokenConfigurer;
import edu.nc.servicebus.model.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private TokenProvider tokenProvider;

    /*public SecurityConfigurer(TokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .csrf().disable()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .authorizeRequests()
               .antMatchers("/").permitAll()
               .antMatchers("/endpoint/**").permitAll()
               .antMatchers("/user").permitAll()
               .antMatchers("/user/**").permitAll()
               .antMatchers("/request/**").permitAll()
               .antMatchers("/response/**").permitAll()
               .antMatchers("/log/**").permitAll()
               .antMatchers("/error/**").permitAll()
               .antMatchers("/validate").permitAll()
               .antMatchers("/**/*.js").permitAll()
               .antMatchers("/**/*.css", "/**/*.{png,jpeg,jpg,svg,ico}").permitAll()
               .anyRequest().fullyAuthenticated()
               .and()
               .apply(new TokenConfigurer(this.tokenProvider));
    }
}

