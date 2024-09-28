package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public CartService() {
    }

    public List<Cart> getAllCarts() {
        return this.cartRepository.findAll();
    }

    public Cart getCartById(Long id) {
        return (Cart)this.cartRepository.findById(id).orElse((Cart) null);
    }

    public Cart getCartByUserId(Long userId) {
        return this.cartRepository.findByUserId(userId);
    }

    public Cart createCart(Cart cart) {
        return (Cart)this.cartRepository.save(cart);
    }

    public void deleteCart(Long id) {
        this.cartRepository.deleteById(id);
    }

}