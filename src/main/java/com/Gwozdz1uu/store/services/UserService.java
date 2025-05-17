package com.Gwozdz1uu.store.services;

import com.Gwozdz1uu.store.entities.*;
import com.Gwozdz1uu.store.repositories.*;
import com.Gwozdz1uu.store.repositories.specifications.ProductSpec;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
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

    @Transactional
    public void updateProductPrice(){
        productRepository.updatePriceByCategory(BigDecimal.valueOf(10),(byte)1);
    }

    @Transactional
    public void fetchProducts(){
        var product = new Product();
        product.setName("product");

        var matcher = ExampleMatcher.matching()
                .withIncludeNullValues()
                .withIgnorePaths("id","description")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(product, matcher);
        var products = productRepository.findAll(example);
        products.forEach(System.out::println);
    }

    public void fetchProductsByCriteria(){
        var products =  productRepository.findProductsByCriteria("prod", BigDecimal.valueOf(1), BigDecimal.valueOf(10));
        products.forEach(System.out::println);
    }

    public void fetchProductsBySpecifications(String name, BigDecimal minPrice, BigDecimal maxPrice){
        Specification<Product> spec = Specification.where(null);

        if(name != null){
            spec = spec.and(ProductSpec.hasName(name));
        }
        if(minPrice!=null){
            spec = spec.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }
        if(maxPrice!=null){
            spec = spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }

        productRepository.findAll(spec).forEach(System.out::println);
    }

    public void fetchSortedProducts(){
        var sort = Sort.by("name","price").and(
                Sort.by("price").descending()
        );

        productRepository.findAll(sort).forEach(System.out::println);
    }

    public void fetchPaginatedProducts(int pageNumber, int size){
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        Page<Product> page = productRepository.findAll(pageRequest);

        var products = page.getContent();
        products.forEach(System.out::println);

        var totalPages = page.getTotalPages();
        var totalElements = page.getTotalElements();
        System.out.println("Total Pages: " + totalPages);
        System.out.println("Total Elements: " + totalElements);
    }

    @Transactional
    public void fetchUsers(){
        var users = userRepository.findAllWithAddresses();
        users.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }

    @Transactional
    public void printLoyalProfiles(){
        var users = userRepository.findLoyalUsers(2);
        users.forEach(u -> {
            System.out.println(u.getId() + ": " + u.getEmail());
        });
    }
}
