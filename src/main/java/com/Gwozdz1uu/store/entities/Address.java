package com.Gwozdz1uu.store.entities;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="addresses")
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="streetfood")
    private String street;

    @Column(name="city")
    private String city;

    @Column(name="zip")
    private String zip;

    @Column(name="state")
    private String state;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}
