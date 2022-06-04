/**
 * @author - Chamath_Wijayarathna
 * Date :6/4/2022
 */

package com.cloudofgoods.springsecurity.springSecurity.security;

import com.cloudofgoods.springsecurity.springSecurity.filter.CustomAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


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

        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder); // Defined PasswordEncoder bean in Main Class
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
    }
}
