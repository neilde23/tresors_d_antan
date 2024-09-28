package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long cart_id;
    private Long user_id;

    public Cart() {
    }

    public Cart(Long user_id) {
        this.user_id = user_id;
    }

    public Long getId() {
        return this.cart_id;
    }

    public Long getUserId() {
        return this.user_id;
    }

    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public Long getCartId() {
        return this.cart_id;
    }

    public void setCartId(Long cart_id) {
        this.cart_id = cart_id;
    }


}
