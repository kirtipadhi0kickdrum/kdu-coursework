package com.example.springassessment.service;

import com.example.springassessment.entity.Order;
import com.example.springassessment.entity.OrderItem;
import com.example.springassessment.entity.ShoppingCart;
import com.example.springassessment.entity.User;
import com.example.springassessment.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Transactional
    public Order placeOrder(Long userId, String shippingAddress) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
        List<ShoppingCart> shoppingCartItems = shoppingCartService.getShoppingCartByUserId(userId);
        if (shoppingCartItems.isEmpty()) {
            throw new IllegalStateException("Shopping cart is empty. Cannot place an order with no items.");
        }
        BigDecimal totalAmount = calculateTotalAmount(shoppingCartItems);
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(totalAmount);
        order.setShippingAddress(shippingAddress);

        List<OrderItem> orderItems = createOrderItems(order, shoppingCartItems);
        order.setOrderItems(orderItems);

        Order savedOrder = orderRepository.save(order);

        shoppingCartService.clearCart(userId);
        return savedOrder;
    }
    private BigDecimal calculateTotalAmount(List<ShoppingCart> shoppingCartItems) {
        return shoppingCartItems.stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    private List<OrderItem> createOrderItems(Order order, List<ShoppingCart> shoppingCartItems) {
        return shoppingCartItems.stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setQuantity(cartItem.getQuantity());
                    return orderItem;
                })
                .collect(Collectors.toList());
    }
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}






















