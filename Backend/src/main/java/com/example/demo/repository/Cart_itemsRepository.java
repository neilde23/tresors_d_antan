package com.example.demo.repository;

import com.example.demo.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Cart_itemsRepository extends JpaRepository<CartItems, Long> {
    List<CartItems> findByCartId(Long cartId);
}