package com.avaya.firebaseadminintegration.components.firebase;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;


public class FirebaseUserOncePerRequestFilter extends OncePerRequestFilter{
	 @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
	        String token = request.getHeader("X-Auth-Token");

	        if (!StringUtils.isEmpty(token)) {
	            try {
	                FirebaseApp firebaseApp = FirebaseApp.getInstance();
	                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(firebaseApp);

	                FirebaseToken firebaseToken = firebaseAuth.verifyIdTokenAsync(token).get();

	                FirebaseUserAuthenticationToken firebaseUserAuthenticationToken = new FirebaseUserAuthenticationToken(firebaseToken);

	                SecurityContextHolder.getContext().setAuthentication(firebaseUserAuthenticationToken);
	            } catch (ExecutionException | InterruptedException e) {
	                e.printStackTrace();
	            }
	        }

	        filterChain.doFilter(request, response);
	    }
}
