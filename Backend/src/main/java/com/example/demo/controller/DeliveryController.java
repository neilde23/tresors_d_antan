package com.example.demo.controller;

import com.example.demo.model.Delivery;
import com.example.demo.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    // 1. Ajouter une livraison
    @PostMapping("/add")
    public ResponseEntity<Delivery> addDelivery(@RequestBody Delivery delivery) {
        try {
            Delivery newDelivery = deliveryService.createDelivery(delivery);
            return new ResponseEntity<>(newDelivery, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 2. Obtenir la liste de toutes les livraisons
    @GetMapping("")
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        try {
            List<Delivery> deliveries = deliveryService.getAllDeliveries();
            if (deliveries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(deliveries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 3. Obtenir une livraison par ID
    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable("id") Long id) {
        try {
            Delivery delivery = deliveryService.getDeliveryById(id);
            if (delivery != null) {
                return new ResponseEntity<>(delivery, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 4. Mettre à jour une livraison par ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable("id") Long id, @RequestBody Delivery deliveryDetails) {
        try {
            Delivery updatedDelivery = deliveryService.updateDelivery(id, deliveryDetails);
            return new ResponseEntity<>(updatedDelivery, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 5. Supprimer une livraison par ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteDelivery(@PathVariable("id") Long id) {
        try {
            deliveryService.deleteDelivery(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 6. Obtenir la liste de toutes les livraisons par ID de commande
    @GetMapping("/order/{id}")
    public ResponseEntity<List<Delivery>> getDeliveriesByOrderId(@PathVariable("id") Long id) {
        try {
            List<Delivery> deliveries = deliveryService.getDeliveryByOrderId(id);
            if (deliveries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(deliveries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
