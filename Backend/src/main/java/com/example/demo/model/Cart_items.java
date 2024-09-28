package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "cart_items"
)

public class Cart_items {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )

    private Long cart_item_id;
    private Long cart_id;
    private Long article_id;
    private Long quantity;

    public Cart_items() {
    }

    public Cart_items(Long cart_id, Long article_id, Long quantity) {
        this.cart_id = cart_id;
        this.article_id = article_id;
        this.quantity = quantity;
    }

    public Long getId() {
        return this.cart_item_id;
    }

    public Long getCart_id() {
        return this.cart_id;
    }

    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }

    public Long getArticle_id() {
        return this.article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }


}
