package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;  // Utilisation de camelCase

    @Column(name = "user_id")  // Mapper avec la colonne 'user_id' de la BD
    private Long userId;

    @Column(name = "cart_id")  // Mapper avec la colonne 'cart_id' de la BD
    private Long cartId;

    // Enumération Status
    public enum Status {
        PENDING,
        DELIVERED,
        CANCELLED
    }

    // Champ pour stocker le statut de la commande
    @Column(name = "status")
    private Status status;

    @Column(name = "total_price")  // Mapper avec la colonne 'total_price' de la BD
    private Long totalPrice;

    @Column(name = "delivery_address")  // Mapper avec la colonne 'delivery_address' de la BD
    private String deliveryAddress;

    // Constructeurs
    public Orders() {
    }

    public Orders(Long userId, Long cartId, Long totalPrice, String deliveryAddress) {
        this.userId = userId;
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.deliveryAddress = deliveryAddress;
        this.status = Status.PENDING; // Initialisation du statut à PENDING
    }

    // Getters et setters
    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
