package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "payments"
)

public class Payments {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )

    private Long payment_id;
    private Long order_id;
    private Long amount;

    // Enumération Status
    public enum Status {
        PAID,
        DECLINED
    }

    // Champ pour stocker le statut du paiement
    private Status status;

    public Payments() {
    }

    public Payments(Long order_id, Long amount) {
        this.order_id = order_id;
        this.amount = amount;
        this.status = Status.PAID; // Initialisation du statut à PAID
    }

    public Long getId() {
        return this.payment_id;
    }

    public Long getOrderId() {
        return this.order_id;
    }

    public void setOrderId(Long order_id) {
        this.order_id = order_id;
    }

    public Long getAmount() {
        return this.amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
