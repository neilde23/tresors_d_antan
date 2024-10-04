package com.example.demo.controller;

import com.example.demo.model.Payments;
import com.example.demo.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api"})
public class PaymentsController {
    @Autowired
    private PaymentsService paymentsService;

    public PaymentsController() {
    }

    @GetMapping({"/payments"})
    public ResponseEntity<List<Payments>> getAllPayments() {
        try {
            List<Payments> payments = this.paymentsService.getAllPayments();
            if (payments.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping({"/payments/{id}"})
    public ResponseEntity<Payments> getPaymentById(@PathVariable Long id) {
        try {
            Payments payment = this.paymentsService.getPaymentById(id);
            if (payment != null) {
                return new ResponseEntity(payment, HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping({"/payments"})
    public ResponseEntity<Payments> createPayment(@RequestBody Payments payments) {
        try {
            Payments newPayment = this.paymentsService.createPayment(payments);
            return new ResponseEntity(newPayment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping({"/payments/{id}"})
    public ResponseEntity deletePayment(@PathVariable Long id) {
        try {
            this.paymentsService.deletePayment(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping({"/payments/{id}"})
    public ResponseEntity<Payments> updatePayment(@PathVariable Long id, @RequestBody Payments payments) {
        try {
            Payments updatedPayment = this.paymentsService.updatePayment(id, payments);
            if (updatedPayment != null) {
                return new ResponseEntity(updatedPayment, HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
