package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "articles"
)

public class Articles {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )

    private Long article_id;
    private String name;
    private Long category_id;
    private Long price;
    private String year;
    private Long stock;
    private boolean available;
    private String description;
    private String image_url;

    public Articles() {
    }

    public Articles(String name, Long category_id, Long price, String year, Long stock, boolean available, String description, String image_url) {
        this.name = name;
        this.category_id = category_id;
        this.price = price;
        this.year = year;
        this.stock = stock;
        this.available = available;
        this.description = description;
        this.image_url = image_url;
    }

    public Long getId() {
        return this.article_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategory() {
        return this.category_id;
    }

    public void setCategory(Long category_id) {
        this.category_id = category_id;
    }

    public Long getPrice() {
        return this.price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Long getStock() {
        return this.stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return this.image_url;
    }

    public void setImageUrl(String image_url) {
        this.image_url = image_url;
    }




}
