package com.Gwozdz1uu.store.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Builder
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Byte id;

    @Id
    @Column(name="name")
    private String name;

    @OneToOne(mappedBy = "category")
    @Builder.Default
    private Set<Product> products = new HashSet<>();

    public void AddProduct(Product product){
        products.add(product);
        product.setCategory(this);
    }

    public void RemoveProduct(Product product){
        products.remove(product);
        product.setCategory(null);
    }
}
