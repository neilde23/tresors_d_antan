package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "recommendations")

public class Recommendations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendation_id;
    private Long user_id;
    private Long article_id;

    public Recommendations() {
    }

    public Recommendations(Long user_id, Long article_id) {
        this.user_id = user_id;
        this.article_id = article_id;
    }

    public Long getId() {
        return this.recommendation_id;
    }

    public Long getUserId() {
        return this.user_id;
    }

    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public Long getProductId() {
        return this.article_id;
    }

    public void setProductId(Long product_id) {
        this.article_id = product_id;
    }


}
