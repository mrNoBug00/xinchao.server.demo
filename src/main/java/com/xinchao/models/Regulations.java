package com.xinchao.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "regulations")
@Getter
@Setter
public class Regulations {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private String content;

    // Constructors, Getters, Setters
    public Regulations() {}

    public Regulations(String title, String content) {
        this.title = title;
        this.content = content;
    }


}

