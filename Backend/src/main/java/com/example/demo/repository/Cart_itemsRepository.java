package com.example.demo.repository;

import com.example.demo.model.Cart_items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Cart_itemsRepository extends JpaRepository<Cart_items, Long> {
    List<Cart_items> findByCartId(Long cartId);
}