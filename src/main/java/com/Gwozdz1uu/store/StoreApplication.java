package com.Gwozdz1uu.store;

import com.Gwozdz1uu.store.entities.User;
import com.Gwozdz1uu.store.repositories.UserRepository;
import com.Gwozdz1uu.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
//		var repository = context.getBean(UserRepository.class);

//		var user = repository.findById(1L).orElseThrow();
//		System.out.println(user.getEmail());

//		repository.findAll().forEach(u -> System.out.println(u.getEmail()));

//		repository.deleteById(1L);

		var service = context.getBean(UserService.class);
		service.fetchProducts();

	}

}
