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
