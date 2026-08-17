package com.keyin.warehouse.controller;

import com.keyin.warehouse.model.OrderItem;
import com.keyin.warehouse.model.Order;
import com.keyin.warehouse.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PostMapping("/{orderId}/items")
    public OrderItem addItemToOrder(
            @PathVariable Long orderId,
            @RequestParam Long productId,
            @RequestParam int quantity) {

        return orderService.addItemToOrder(orderId, productId, quantity);
    }

    @PostMapping("/add-to-priority-tree")
    public Order addToPriorityTree(@RequestBody Order order) {
        return orderService.addToPriorityTree(order);
    }

    @GetMapping("/priority/inorder")
    public List<Order> getPriorityInorder() {
        return orderService.getPriorityInorder();
    }

    @GetMapping("/priority/highest")
    public Order getHighestPriority() {
        return orderService.getHighestPriority();
    }

    @GetMapping("/priority/lowest")
    public Order getLowestPriority() {
        return orderService.getLowestPriority();
    }

}