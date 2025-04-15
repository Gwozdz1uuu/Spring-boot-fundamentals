package com.Gwozdz1uu.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name="id")
    private Long id;

    @Column(nullable = false,name = "name")
    private String name;

    @Column(nullable = false,name = "email")
    private String email;

    @Column(nullable = false,name = "password")
    private String password;

    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST, CascadeType.REMOVE},orphanRemoval = true)
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();

    public void AddAddress(Address address){
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Address address){
        addresses.remove(address);
        address.setUser(null);
    }

    public void addTag(String tagName){
        var tag = new Tag(tagName);
        tags.add(tag);
        tag.getUsers().add(this);
    }


    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Profile profile;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "user_tags",
            joinColumns = @JoinColumn(name ="user_id"),
            inverseJoinColumns = @JoinColumn(name="tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "wishlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> wishlist = new HashSet<>();

    public void addFavoriteProduct(Product product) {
        wishlist.add(product);
    }
}
