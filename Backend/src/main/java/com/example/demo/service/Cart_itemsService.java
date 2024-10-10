package com.example.demo.service;

import com.example.demo.model.CartItems;
import com.example.demo.repository.Cart_itemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cart_itemsService {
    @Autowired
    private Cart_itemsRepository cart_itemsRepository;

    public Cart_itemsService() {
    }

    public List<CartItems> getAllCart_items() {
        return this.cart_itemsRepository.findAll();
    }

    public CartItems getCart_itemsById(Long id) {
        return (CartItems)this.cart_itemsRepository.findById(id).orElse((CartItems) null);
    }

    public List<CartItems> getCart_itemsByCartId(Long cartId) {
        return this.cart_itemsRepository.findByCartId(cartId);
    }

    public CartItems createCart_items(CartItems cart_items) {
        return (CartItems)this.cart_itemsRepository.save(cart_items);
    }

    public void deleteCart_items(Long id) {
        this.cart_itemsRepository.deleteById(id);
    }

    public CartItems updateCart_itemsQuantity(Long id, Long quantity) {
        CartItems cart_item = (CartItems)this.cart_itemsRepository.findById(id).orElse((CartItems) null);
        if (cart_item != null) {
            cart_item.setQuantity(quantity);
            return (CartItems)this.cart_itemsRepository.save(cart_item);
        } else {
            return (CartItems) null;
        }
    }

    public void deleteCart_itemsByCartId(Long cartId) {
        List<CartItems> cartItems = this.cart_itemsRepository.findByCartId(cartId);
        if (!cartItems.isEmpty()) {
            this.cart_itemsRepository.deleteAll(cartItems);
        }
    }
}