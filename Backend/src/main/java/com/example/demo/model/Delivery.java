package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "delivery")

public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long delivery_id;
    private Long order_id;
    private String delivery_address;
    private String estimated_delivery_date;
    private String tracking_number;

    // Enumération Status
    public enum Status {
        PENDING,
        SHIPPED,
        DELIVERED,
    }

    // Champ pour stocker le statut de la livraison
    private Status status;


    public Delivery() {
    }

    public Delivery(Long order_id, String delivery_address, String estimated_delivery_date, String tracking_number) {
        this.order_id = order_id;
        this.delivery_address = delivery_address;
        this.estimated_delivery_date = estimated_delivery_date;
        this.tracking_number = tracking_number;
        this.status = Status.PENDING; // Initialisation du statut à PENDING
    }

    public Long getId() {
        return this.delivery_id;
    }

    public Long getOrderId() {
        return this.order_id;
    }

    public void setOrderId(Long order_id) {
        this.order_id = order_id;
    }

    public String getDeliveryAddress() {
        return this.delivery_address;
    }

    public void setDeliveryAddress(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getEstimatedDeliveryDate() {
        return this.estimated_delivery_date;
    }

    public void setEstimatedDeliveryDate(String estimated_delivery_date) {
        this.estimated_delivery_date = estimated_delivery_date;
    }

    public String getTrackingNumber() {
        return this.tracking_number;
    }

    public void setTrackingNumber(String tracking_number) {
        this.tracking_number = tracking_number;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
