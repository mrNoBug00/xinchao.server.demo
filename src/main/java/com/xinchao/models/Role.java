package com.xinchao.models;


import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleEnum name;

    @Column(nullable = false)
    private String description;



    public Integer getId() {
        return id;
    }

    public RoleEnum getName() {
        return name;
    }

    public Role setName(RoleEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Role setDescription(String description) {
        this.description = description;
        return this;
    }



    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description +
                '}';
    }
}