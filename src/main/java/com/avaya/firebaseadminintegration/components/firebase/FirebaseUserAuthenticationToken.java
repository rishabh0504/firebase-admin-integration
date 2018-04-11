package com.avaya.firebaseadminintegration.components.firebase;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import com.google.firebase.auth.FirebaseToken;

public class FirebaseUserAuthenticationToken extends AbstractAuthenticationToken{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FirebaseToken firebaseToken;

    public FirebaseUserAuthenticationToken(FirebaseToken firebaseToken) {
        super(null);

        this.firebaseToken = firebaseToken;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return firebaseToken;
    }
}
