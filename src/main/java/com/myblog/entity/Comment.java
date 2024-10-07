package com.myblog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data // it is lombok annotation using for avoid getters and setters(boilerplate code)
@AllArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String body;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;
    public Comment() {}
    public Comment(String name, String email, String body) {
        this.name = name;
        this.email = email;
        this.body = body;
    }
}

