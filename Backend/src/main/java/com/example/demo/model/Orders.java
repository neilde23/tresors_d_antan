package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    private Long user_id;
    private Long cart_id;

    // Enumération Status
    public enum Status {
        PENDING,
        DELIVERED,
        CANCELLED
    }

    // Champ pour stocker le statut de la commande
    private Status status;

    private Long total_price;
    private String delivery_address;

    public Orders() {
    }

    public Orders(Long user_id, Long cart_id, Long total_price, String delivery_address) {
        this.user_id = user_id;
        this.cart_id = cart_id;
        this.total_price = total_price;
        this.delivery_address = delivery_address;
        this.status = Status.PENDING; // Initialisation du statut à PENDING
    }

    public Long getId() {
        return this.order_id;
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

    public Long getTotalPrice() {
        return this.total_price;
    }

    public void setTotalPrice(Long total_price) {
        this.total_price = total_price;
    }

    public String getDeliveryAddress() {
        return this.delivery_address;
    }

    public void setDeliveryAddress(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
