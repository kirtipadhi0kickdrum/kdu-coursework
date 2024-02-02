package com.example.springassessment.controller;

import com.example.springassessment.entity.ShoppingCart;
import com.example.springassessment.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/shopping-cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;


    @GetMapping("/{userId}")
    public ResponseEntity<List<ShoppingCart>> getShoppingCartByUserId(@PathVariable Long userId) {
        List<ShoppingCart> shoppingCart = shoppingCartService.getShoppingCartByUserId(userId);
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }
    @PostMapping("/add-to-cart/{userId}/{productId}/{quantity}")
    public ResponseEntity<ShoppingCart> addToCart(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @PathVariable int quantity) {
        ShoppingCart cartItem = shoppingCartService.addToCart(userId, productId, quantity);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }
    @DeleteMapping("/remove-from-cart/{userId}/{productId}")
    public ResponseEntity<Void> removeFromCart(
            @PathVariable Long userId,
            @PathVariable Long productId) {
        shoppingCartService.removeFromCart(userId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/update-quantity/{userId}/{productId}/{quantity}")
    public ResponseEntity<Void> updateCartItemQuantity(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @PathVariable int quantity) {
        shoppingCartService.updateCartItemQuantity(userId, productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/clear-cart/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        shoppingCartService.clearCart(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}






















