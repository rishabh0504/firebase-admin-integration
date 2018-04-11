package com.avaya.firebaseadminintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.avaya.firebaseadminintegration")
public class FirebaseAdminIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirebaseAdminIntegrationApplication.class, args);
	}
}
