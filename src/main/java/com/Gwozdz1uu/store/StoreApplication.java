package com.Gwozdz1uu.store;

import com.Gwozdz1uu.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
//		ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
		var user = User.builder()
				.name("Mati")
				.email("M@gmail.com")
				.password("elomordo")
				.build();
	}

}
