package com.example.springassessment.controller;

import com.example.springassessment.entity.Order;
import com.example.springassessment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService)
    {
        this.orderService = orderService;
    }

    @PostMapping("/place-order/{userId}")
    public ResponseEntity<Order> placeOrder(
            @PathVariable Long userId,
            @RequestParam String shippingAddress) {
        Order order = orderService.placeOrder(userId, shippingAddress);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    @GetMapping("/user-orders/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
