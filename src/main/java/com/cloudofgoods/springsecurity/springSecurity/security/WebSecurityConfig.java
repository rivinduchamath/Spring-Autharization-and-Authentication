package com.cloudofgoods.springsecurity.springSecurity.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
 /* The WebSecurityConfig class is annotated with "@EnableWebSecurity"
    to enable Spring Security's web security support and provide the
    Spring MVC integration.*/
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {/* WebSecurityConfigurerAdapter, Tell to the spring How we want
                                                                        to manage application to the users and the security in application
                                                                        (Allows customization to both WebSecurity and HttpSecurity) */
    private final UserDetailsService userDetailsService;   /*Provided By Spring Security used to retrieve the user's
                                                           authentication and authorization information. (feed the user information to the Spring security API.)*/
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {  /*AuthenticationManagerBuilder Allows for easily
                                                                                 building in memory authentication,
                                                                                 LDAP authentication, JDBC based authentication etc...*/
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
