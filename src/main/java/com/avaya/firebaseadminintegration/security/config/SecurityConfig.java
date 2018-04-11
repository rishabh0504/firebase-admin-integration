package com.avaya.firebaseadminintegration.security.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.avaya.firebaseadminintegration.components.firebase.FirebaseUserOncePerRequestFilter;
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new FirebaseUserOncePerRequestFilter(), BasicAuthenticationFilter.class);
    }
	
}	
