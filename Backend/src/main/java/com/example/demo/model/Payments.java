package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "payments")
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;  // Utilisation de camelCase

    @Column(name = "order_id")  // Mapper avec la colonne 'order_id' de la BD
    private Long orderId;

    @Column(name = "amount")  // Mapper avec la colonne 'amount' de la BD
    private Long amount;

    // Enumération Status
    public enum Status {
        PAID,
        DECLINED
    }

    // Champ pour stocker le statut du paiement
    @Column(name = "status")  // Mapper avec la colonne 'status' de la BD
    private Status status;

    // Constructeurs

    public Payments() {
    }

    public Payments(Long orderId, Long amount) {
        this.orderId = orderId;
        this.amount = amount;
        this.status = Status.PAID; // Initialisation du statut à PAID
    }

    // Getters et setters
    public Long getPaymentId() {
        return paymentId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
