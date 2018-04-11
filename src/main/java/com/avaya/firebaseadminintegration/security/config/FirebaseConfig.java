package com.avaya.firebaseadminintegration.security.config;

import java.io.IOException;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseConfig implements DisposableBean {

	@Value("${com.avaya.fleetmanagement.databaseUrl}")
	private String databaseUrl;

	@Value("${com.avaya.fleetmanagement.serviceAccountKey}")
	private String serviceAccount;

	@Bean
	public FirebaseApp website() throws IOException {
		Resource resource = new ClassPathResource(serviceAccount);

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(resource.getInputStream())).setDatabaseUrl(databaseUrl)
				.build();

		return FirebaseApp.initializeApp(options);
	}

	@Override
	public void destroy() throws Exception {
		for (FirebaseApp firebaseApp : FirebaseApp.getApps()) {
			firebaseApp.delete();
		}
	}
}
