package com.example.demo.service;

import com.example.demo.model.Cart_items;
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

    public List<Cart_items> getAllCart_items() {
        return this.cart_itemsRepository.findAll();
    }

    public Cart_items getCart_itemsById(Long id) {
        return (Cart_items)this.cart_itemsRepository.findById(id).orElse((Cart_items) null);
    }

    public List<Cart_items> getCart_itemsByCartId(Long cartId) {
        return this.cart_itemsRepository.findByCartId(cartId);
    }

    public Cart_items createCart_items(Cart_items cart_items) {
        return (Cart_items)this.cart_itemsRepository.save(cart_items);
    }

    public void deleteCart_items(Long id) {
        this.cart_itemsRepository.deleteById(id);
    }

    public Cart_items updateCart_items(Long id, Cart_items cart_items) {
        Cart_items cart_item = (Cart_items)this.cart_itemsRepository.findById(id).orElse((Cart_items) null);
        if (cart_item != null) {
            cart_item.setQuantity(cart_items.getQuantity());
            return (Cart_items)this.cart_itemsRepository.save(cart_item);
        } else {
            return (Cart_items) null;
        }
    }
}