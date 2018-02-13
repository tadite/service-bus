package edu.nc.servicebus.model.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        //auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/error/allErrors").permitAll()
                .antMatchers("/error/save").permitAll()
                .antMatchers("/error/delete").permitAll()
                .antMatchers("/log/allLogs").permitAll()
                .antMatchers("/log/save").permitAll()
                .antMatchers("/log/delete").permitAll()
                .antMatchers("/user/allUsers").permitAll()
                .antMatchers("/user/save").permitAll()
                .antMatchers("/user/delete").permitAll()
                .antMatchers("/request/allRequests").permitAll()
                .antMatchers("/request/save").permitAll()
                .antMatchers("/request/delete").permitAll()
                .antMatchers("/response/allResponses").permitAll()
                .antMatchers("/response/save").permitAll()
                .antMatchers("/response/delete").permitAll()
                .antMatchers("/registration").hasRole("ADMIN")
                .antMatchers("/**").fullyAuthenticated()
                .and()
                .formLogin()//.loginPage("/")
                .permitAll()
                .and()
                .logout().permitAll();
    }
}
