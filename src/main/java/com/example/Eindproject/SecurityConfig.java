package com.example.Eindproject;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    In deze configuratie stellen we in dat we van memorie autenticatie gebruik maken en voegen een aantal accounts toe met verschillende gebruikersrollen.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("{noop}admin").roles("ADMIN")
                .and()
                .withUser("administratief").password("{noop}administratief").roles("ADMINISTRATION")
                .and()
                .withUser("monteur").password("{noop}monteur").roles("MECHANIC")
                .and()
                .withUser("kassa").password("{noop}kassa").roles("CASHIER")
                .and()
                .withUser("backoffice").password("{noop}backoffice").roles("BACKOFFICE");
    }

//    In deze configuratie stellen we in dat je moet inloggen op basis van formlogin en geven we aan naar welke links je mag gaan met welke gebruikersrollen (authorizatie)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .and()
                .authorizeRequests()
                .antMatchers("/administration/**").hasAnyRole("ADMINISTRATION", "ADMIN")
                .antMatchers("/backoffice/**").hasAnyRole("BACKOFFICE", "ADMIN")
                .antMatchers("/mechanic/**").hasAnyRole("MECHANIC", "ADMIN")
                .antMatchers("/cashier/**").hasAnyRole("CASHIER", "ADMIN")
                .anyRequest()
                .authenticated();
    }
}
