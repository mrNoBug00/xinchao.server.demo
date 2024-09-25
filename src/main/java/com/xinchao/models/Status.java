package com.xinchao.models;


import jakarta.persistence.*;

@Entity
@Table(name = "status")
public class Status {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(unique = true, nullable = false)
  private StatusEnum name;

  @Column(nullable = false)
  private String description;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public Status setDescription(String description) {
    this.description = description;
    return this;
  }

  public StatusEnum getName() {
    return name;
  }

  public void setName(StatusEnum name) {
    this.name = name;
  }


  @Override
  public String toString() {
    return "Status{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description +
        '}';
  }
}
