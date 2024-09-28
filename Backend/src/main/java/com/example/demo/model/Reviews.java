package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")


public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long review_id;
    private Long user_id;
    private Long article_id;
    private int rating; // entre 1 et 5
    private String comment;
    // a voir avec les attributs de type date et time

    public Reviews() {
    }

    public Reviews(Long user_id, Long article_id, int rating, String comment) {
        this.user_id = user_id;
        this.article_id = article_id;
        this.rating = rating;
        this.comment = comment;
    }

    public Long getId() {
        return this.review_id;
    }

    public Long getUserId() {
        return this.user_id;
    }

    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public Long getArticleId() {
        return this.article_id;
    }

    public void setArticleId(Long article_id) {
        this.article_id = article_id;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        /*if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }*/
        this.rating = rating;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }





}
