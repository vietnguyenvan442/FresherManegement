package com.example.fresher_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FresherManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(FresherManagementApplication.class, args);
    }

//		@Bean
//		CommandLineRunner run(UserService userService){
//			return args ->{
//	//			userService.saveAdmin();
//				userService.saveManager();
//			};
//		}
}
