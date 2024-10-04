package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "recommendations")
public class Recommendations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendationId;  // Utilisation de camelCase

    @Column(name = "user_id")  // Mapper avec la colonne 'user_id' de la BD
    private Long userId;

    @Column(name = "article_id")  // Mapper avec la colonne 'article_id' de la BD
    private Long articleId;

    // Constructeurs
    public Recommendations() {
    }

    public Recommendations(Long userId, Long articleId) {
        this.userId = userId;
        this.articleId = articleId;
    }

    // Getters et setters
    public Long getRecommendationId() {
        return recommendationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}
