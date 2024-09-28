package com.example.demo.repository;

import com.example.demo.model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {
    Payments findByOrderId(Long orderId);
}