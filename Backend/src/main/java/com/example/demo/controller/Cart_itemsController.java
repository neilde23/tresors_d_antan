package com.example.demo.controller;

import com.example.demo.model.CartItems;
import com.example.demo.service.Cart_itemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class Cart_itemsController {

    @Autowired
    private Cart_itemsService Cart_itemsRepository;

    // 1. Ajouter un article au panier
    @PostMapping("/add")
    public ResponseEntity<CartItems> addCartItem(@RequestBody CartItems cartItem) {
        try {
            // afficher le contenu du panier
            System.out.println(cartItem);
            CartItems newCartItem = Cart_itemsRepository.createCart_items(cartItem);
            return new ResponseEntity<>(newCartItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 2. Obtenir la liste de tous les articles du panier
    @GetMapping("")
    public ResponseEntity<List<CartItems>> getAllCartItems() {
        try {
            List<CartItems> cartItems = Cart_itemsRepository.getAllCart_items();
            if (cartItems.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cartItems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 3. Obtenir un article par ID
    @GetMapping("/{id}")
    public ResponseEntity<CartItems> getCartItemById(@PathVariable("id") Long id) {
        try {
            CartItems cartItem = Cart_itemsRepository.getCart_itemsById(id);
            if (cartItem != null) {
                return new ResponseEntity<>(cartItem, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 4. Mettre à jour la quantité d'un article par ID
    @PutMapping("/update/{id}")
    public ResponseEntity<CartItems> updateCartItem(@PathVariable("id") Long id, @RequestBody Long quantity) {
        try {
            if (quantity <= 0) {
                Cart_itemsRepository.deleteCart_items(id);
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            CartItems updatedCartItem = Cart_itemsRepository.updateCart_itemsQuantity(id, quantity);
            return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 5. Supprimer un article du panier par ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCartItem(@PathVariable("id") Long id) {
        try {
            Cart_itemsRepository.deleteCart_items(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/cart/{id}")
    public ResponseEntity<HttpStatus> deleteCartItemsByCartId(@PathVariable("id") Long id) {
        try {
            Cart_itemsRepository.deleteCart_itemsByCartId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 6. Trouver les articles d'un panier
    @GetMapping("/cart/{id}")
    public ResponseEntity<List<CartItems>> getCartItemsByCartId(@PathVariable("id") Long id) {
        try {
            List<CartItems> cartItems = Cart_itemsRepository.getCart_itemsByCartId(id);
            if (cartItems.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cartItems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
