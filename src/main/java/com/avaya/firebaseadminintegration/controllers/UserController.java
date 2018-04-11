package com.avaya.firebaseadminintegration.controllers;

import java.security.Principal;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avaya.firebaseadminintegration.components.user.CurrentUser;
import com.avaya.firebaseadminintegration.services.FirebaseUserService;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

@RestController
public class UserController {

	@Autowired
	private FirebaseUserService firebaseUserService;

	@RequestMapping("/test")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public String userIndex(@CurrentUser Principal principal) {
		return "Hello, " + principal.getName();
	}

	@GetMapping
	public UserRecord getUser(@RequestParam("keyword") String keyword)
			throws FirebaseAuthException, ExecutionException, InterruptedException {
		return firebaseUserService.findUserByAll(keyword);
	}
}
