package com.example.relationshipJPA;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@EnableScheduling
@SpringBootApplication
public class RelationshipJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelationshipJpaApplication.class, args);
	}


	// enable global level CORS using WebMvcConfigure class by overriding method corsConfigure()
    @Bean
    WebMvcConfigurer corsConfigure() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000").
						allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}
}
