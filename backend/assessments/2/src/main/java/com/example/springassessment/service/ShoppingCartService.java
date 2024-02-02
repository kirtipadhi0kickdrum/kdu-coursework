package com.example.springassessment.service;

import com.example.springassessment.entity.Product;
import com.example.springassessment.entity.ShoppingCart;
import com.example.springassessment.entity.User;
import com.example.springassessment.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    public List<ShoppingCart> getShoppingCartByUserId(Long userId) {
        return shoppingCartRepository.findByUserId(userId);
    }
    public ShoppingCart addToCart(Long userId, Long productId, int quantity) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + productId));
        Optional<ShoppingCart> existingCartItem = shoppingCartRepository.findByUserIdAndProductId(userId, productId);
        if (existingCartItem.isPresent()) {
            ShoppingCart cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            return shoppingCartRepository.save(cartItem);
        } else {
            ShoppingCart newCartItem = new ShoppingCart();
            newCartItem.setUser(user);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            return shoppingCartRepository.save(newCartItem);
        }
    }
    public void removeFromCart(Long userId, Long productId) {
        shoppingCartRepository.findByUserIdAndProductId(userId, productId)
                .ifPresent(shoppingCartRepository::delete);
    }
    public void updateCartItemQuantity(Long userId, Long productId, int quantity) {
        shoppingCartRepository.findByUserIdAndProductId(userId, productId)
                .ifPresent(cartItem -> {
                    cartItem.setQuantity(quantity);
                    shoppingCartRepository.save(cartItem);
                });
    }
    public void clearCart(Long userId) {
        List<ShoppingCart> userCart = shoppingCartRepository.findByUserId(userId);
        shoppingCartRepository.deleteAll(userCart);
    }
}






















