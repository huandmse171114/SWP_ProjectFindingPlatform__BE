package com.findhub.finhubbackend;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectFindingPlatformApplication {

	public static void main(String[] args) {
		// StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		// 		.configure()
		// 		.build();

		// SessionFactory factory = new MetadataSources(registry)
		// 		.buildMetadata()
		// 		.buildSessionFactory();

		// Session session = factory.openSession();

		// session.close();
		// factory.close();

		SpringApplication.run(ProjectFindingPlatformApplication.class, args);
	}
}
