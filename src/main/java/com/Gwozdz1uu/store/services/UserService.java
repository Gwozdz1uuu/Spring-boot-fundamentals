package com.Gwozdz1uu.store.services;

import com.Gwozdz1uu.store.entities.*;
import com.Gwozdz1uu.store.repositories.AddressRepository;
import com.Gwozdz1uu.store.repositories.ProfileRepository;
import com.Gwozdz1uu.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final EntityManager entityManager;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void showEntityStates(){
        var user = User.builder()
                .name("Guest")
                .email("guest@gmail.com")
                .password("guest")
                .build();
        if (entityManager.contains(user)){
            System.out.println("Persistent");
        }
        else
            System.out.println("Transient / Detached");

        userRepository.save(user);

        if (entityManager.contains(user)){
            System.out.println("Persistent");
        }
        else
            System.out.println("Transient / Detached");

    }

    @Transactional
    public void showRelatedEntities(){
        var profile = profileRepository.findById(2L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }

    public void fetchAddress(){
        var address = addressRepository.findById(1L).orElseThrow();
    }

    public void persistRelated(){
        var user = User.builder()
                .name("Guest")
                .email("guest@gmail.com")
                .password("guest")
                .build();

        var address = Address.builder()
                .street("Street")
                .city("City")
                .state("State")
                .zip("Zip")
                .build();

        user.AddAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void deleteRelated(){
        var user = userRepository.findById(3L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void manageProducts(){
        var user = userRepository.findById(3L).orElseThrow();
        var products = productRepository.findAll();
        products.forEach(user::addFavoriteProduct);
        userRepository.save(user);
    }
    @Transactional
    public void removeAllProducts(){
        productRepository.deleteById(4L);
    }
}
