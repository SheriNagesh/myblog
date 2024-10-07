package com.myblog.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 60, unique = true, nullable = false)
    private String name; // Role names should be unique (e.g., ROLE_USER, ROLE_ADMIN)

    @ManyToMany(mappedBy = "roles")
    private Set<User> users; // Bidirectional mapping to the User entity
}
