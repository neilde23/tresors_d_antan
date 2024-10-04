package com.example.demo.service;

import com.example.demo.model.Delivery;
import com.example.demo.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;

    public DeliveryService() {
    }

    public List<Delivery> getAllDeliveries() {
        return this.deliveryRepository.findAll();
    }

    public Delivery getDeliveryById(Long id) {
        return (Delivery)this.deliveryRepository.findById(id).orElse((Delivery) null);
    }

    public List<Delivery> getDeliveryByOrderId(Long orderId) {
        return this.deliveryRepository.findByOrderId(orderId);
    }

    public Delivery createDelivery(Delivery delivery) {
        return (Delivery)this.deliveryRepository.save(delivery);
    }

    public void deleteDelivery(Long id) {
        this.deliveryRepository.deleteById(id);
    }

    public Delivery updateDelivery(Long id, Delivery delivery) {
        Delivery delivery1 = (Delivery)this.deliveryRepository.findById(id).orElse((Delivery) null);
        if (delivery1 != null) {
            delivery1.setDeliveryAddress(delivery.getDeliveryAddress());
            delivery1.setOrderId(delivery.getOrderId());
            delivery1.setStatus(delivery.getStatus());
            return (Delivery)this.deliveryRepository.save(delivery1);
        } else {
            return (Delivery) null;
        }
    }
}