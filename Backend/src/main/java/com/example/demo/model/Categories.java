package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "categories"
)
public class Categories {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long category_id;
    private String name;

    public Categories() {
    }

    public Categories(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.category_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
