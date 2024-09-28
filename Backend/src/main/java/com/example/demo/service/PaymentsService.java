package com.example.demo.service;

import com.example.demo.model.Payments;
import com.example.demo.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentsService {
    @Autowired
    private PaymentsRepository paymentsRepository;

    public PaymentsService() {
    }

    public List<Payments> getAllPayments() {
        return this.paymentsRepository.findAll();
    }

    public Payments getPaymentById(Long id) {
        return (Payments)this.paymentsRepository.findById(id).orElse((Payments) null);
    }

    public Payments getPaymentByOrderId(Long orderId) {
        return (Payments)this.paymentsRepository.findByOrderId(orderId);
    }

    public Payments createPayment(Payments payments) {
        return (Payments)this.paymentsRepository.save(payments);
    }

    public void deletePayment(Long id) {
        this.paymentsRepository.deleteById(id);
    }

    public Payments updatePayment(Long id, Payments payments) {
        Payments payment = (Payments)this.paymentsRepository.findById(id).orElse((Payments) null);
        if (payment != null) {
            payment.setAmount(payments.getAmount());
            payment.setStatus(payments.getStatus());
            return (Payments)this.paymentsRepository.save(payment);
        } else {
            return (Payments) null;
        }
    }
}