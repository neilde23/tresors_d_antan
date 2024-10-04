package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "articles")
public class Articles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;  // Utilisation de camelCase

    private String name;

    private Long categoryId;  // Utilisation de camelCase

    private Long price;

    private String year;

    private Long stock;

    private boolean available;

    private String description;

    private String imageUrl;  // Utilisation de camelCase

    // Constructeurs
    public Articles() {
    }

    public Articles(String name, Long categoryId, Long price, String year, Long stock, boolean available, String description, String imageUrl) {
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.year = year;
        this.stock = stock;
        this.available = available;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Getters et setters utilisant camelCase
    public Long getArticleId() {
        return articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    // Ajout des méthodes getCategory() et setCategory() pour compatibilité
    public Long getCategory() {
        return categoryId;
    }

    public void setCategory(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
