package com.onboarding.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class OnboardingMoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnboardingMoviesApplication.class, args);
	}

}
