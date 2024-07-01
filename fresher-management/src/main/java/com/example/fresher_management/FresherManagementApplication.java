package com.example.fresher_management;

import com.example.fresher_management.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FresherManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FresherManagementApplication.class, args);
	}

	//	@Bean
	//	CommandLineRunner run(UserService userService){
	//		return args ->{
	////			userService.saveAdmin();
	//			userService.saveManager();
	//		};
	//	}
}
