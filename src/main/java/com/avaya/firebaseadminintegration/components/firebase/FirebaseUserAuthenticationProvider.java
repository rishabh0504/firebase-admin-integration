package com.avaya.firebaseadminintegration.components.firebase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.google.firebase.auth.FirebaseToken;

@Component
public class FirebaseUserAuthenticationProvider implements AuthenticationProvider {

    private final FirebaseUserDetailsServices firebaseUserDetailsServices;

    @Autowired
    public FirebaseUserAuthenticationProvider(FirebaseUserDetailsServices firebaseUserDetailsServices) {
        this.firebaseUserDetailsServices = firebaseUserDetailsServices;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        FirebaseToken firebaseToken = (FirebaseToken) authentication.getPrincipal();

        UserDetails userDetails = firebaseUserDetailsServices.loadUserByUsername(firebaseToken.getUid());

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return FirebaseUserAuthenticationToken.class.isAssignableFrom(authentication);
    }
}