package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;  // Utilisation de CamelCase

    @Column(name = "order_id")  // Mapper avec la colonne 'order_id' de la BD
    private Long orderId;

    @Column(name = "delivery_address")  // Mapper avec la colonne 'delivery_address' de la BD
    private String deliveryAddress;

    @Column(name = "estimated_delivery_date")  // Mapper avec la colonne 'estimated_delivery_date' de la BD
    private String estimatedDeliveryDate;

    @Column(name = "tracking_number")  // Mapper avec la colonne 'tracking_number' de la BD
    private String trackingNumber;

    // Enumération Status
    public enum Status {
        PENDING,
        SHIPPED,
        DELIVERED
    }

    // Champ pour stocker le statut de la livraison
    @Column(name = "status")  // Mapper avec la colonne 'status' de la BD
    private Status status;

    // Constructeurs
    public Delivery() {
    }

    public Delivery(Long orderId, String deliveryAddress, String estimatedDeliveryDate, String trackingNumber) {
        this.orderId = orderId;
        this.deliveryAddress = deliveryAddress;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.trackingNumber = trackingNumber;
        this.status = Status.PENDING; // Initialisation du statut à PENDING
    }

    // Getters et setters
    public Long getDeliveryId() {
        return deliveryId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(String estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
