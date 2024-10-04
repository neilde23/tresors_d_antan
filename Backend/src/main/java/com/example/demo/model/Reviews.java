package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;  // Utilisation de camelCase

    @Column(name = "user_id")  // Mapper avec la colonne 'user_id' de la BD
    private Long userId;

    @Column(name = "article_id")  // Mapper avec la colonne 'article_id' de la BD
    private Long articleId;

    private Long rating;  // Entre 1 et 5

    private String comment;

    // Constructeurs
    public Reviews() {
    }

    public Reviews(Long userId, Long articleId, Long rating, String comment) {
        this.userId = userId;
        this.articleId = articleId;
        this.rating = rating;
        this.comment = comment;
    }

    // Getters et setters
    public Long getReviewId() {
        return reviewId;
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

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
