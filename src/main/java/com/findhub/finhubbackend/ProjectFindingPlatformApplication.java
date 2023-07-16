package com.findhub.finhubbackend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.auth.oauth2.GoogleCredentials;

@SpringBootApplication
public class ProjectFindingPlatformApplication {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		// 		.configure()
		// 		.build();

		// SessionFactory factory = new MetadataSources(registry)
		// 		.buildMetadata()
		// 		.buildSessionFactory();

		// Session session = factory.openSession();

		// session.close();
		// factory.close();

		// ClassLoader cl = ProjectFindingPlatformApplication.class.getClassLoader();

		// File f = new File(
		// 	Objects.requireNonNull(
		// 		cl.getResource("findhub-private-key.json")
		// 	).getFile()
		// );

		// FileInputStream serviceAccount =
		// 	new FileInputStream(f.getAbsolutePath());

		// FirebaseOptions options = FirebaseOptions.builder()
		// .setCredentials(GoogleCredentials.fromStream(serviceAccount))
		// .build();

		// FirebaseApp.initializeApp(options);

		SpringApplication.run(ProjectFindingPlatformApplication.class, args);
	}
}
