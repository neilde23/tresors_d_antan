package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // 1. Ajouter un panier
    @PostMapping("/add")
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        try {
            Cart newCart = cartService.createCart(cart);
            return new ResponseEntity<>(newCart, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 2. Obtenir la liste de tous les paniers
    @GetMapping("")
    public ResponseEntity<List<Cart>> getAllCarts() {
        try {
            List<Cart> carts = cartService.getAllCarts();
            if (carts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(carts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 3. Obtenir un panier par ID
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable("id") Long id) {
        try {
            Cart cart = cartService.getCartById(id);
            if (cart != null) {
                return new ResponseEntity<>(cart, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    // 4. Mettre à jour un panier par ID
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Cart> updateCart(@PathVariable("id") Long id, @RequestBody Cart cartDetails) {
//        try {
//            Cart updatedCart = cartService.updateCart(id, cartDetails);
//            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    // 5. Supprimer un panier par ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCart(@PathVariable("id") Long id) {
        try {
            cartService.deleteCart(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 6. Obtenir le panier par ID de l'utilisateur
    @GetMapping("/user/{id}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable("id") Long id) {
        try {
            Cart cart = cartService.getCartByUserId(id);
            if (cart != null) {
                return new ResponseEntity<>(cart, HttpStatus.OK);
            }
            // Si le panier n'existe pas, le créer automatiquement
            Cart newCart = cartService.createCart(new Cart(id));
            return new ResponseEntity<>(newCart, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
