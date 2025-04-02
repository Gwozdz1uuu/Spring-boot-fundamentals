package com.Gwozdz1uu.store.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name="tags")
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;
}
