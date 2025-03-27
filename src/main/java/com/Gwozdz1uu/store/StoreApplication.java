package com.Gwozdz1uu.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
		var userService = context.getBean(UserService.class);
		userService.registerUser(new User(1L,"gwozdecki@gmail.com","123123","omamale"));
		userService.registerUser(new User(1L,"gwozdecki@gmail.com","123123","omamale"));


	}

}
