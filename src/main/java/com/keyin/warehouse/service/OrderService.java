package com.keyin.warehouse.service;

import com.keyin.warehouse.datastructure.OrderBST;
import com.keyin.warehouse.model.Customer;
import com.keyin.warehouse.model.Order;
import com.keyin.warehouse.repository.CustomerRepository;
import com.keyin.warehouse.repository.OrderRepository;
import org.springframework.stereotype.Service;
import com.keyin.warehouse.model.OrderItem;
import com.keyin.warehouse.model.Product;
import com.keyin.warehouse.repository.OrderItemRepository;
import com.keyin.warehouse.repository.ProductRepository;

import java.util.List;

@Service
public class OrderService {

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderBST orderBST;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        if (order.getPriorityLevel() < 1 || order.getPriorityLevel() > 10) {
            throw new IllegalArgumentException("Priority level must be between 1 and 10");
        }
        Customer customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        order.setCustomer(customer);

        return orderRepository.save(order);
    }

    public Order addToPriorityTree(Order order) {
        if (order.getPriorityLevel() < 1 || order.getPriorityLevel() > 10) {
            throw new IllegalArgumentException("Priority level must be between 1 and 10");
        }
        Customer customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        order.setCustomer(customer);

        Order savedOrder = orderRepository.save(order);

        orderBST.insert(savedOrder);

        return savedOrder;
    }

    public List<Order> getPriorityInorder() {
        return orderBST.inorder();
    }

    public Order getHighestPriority() {
        return orderBST.findHighest();
    }

    public Order getLowestPriority() {
        return orderBST.findLowest();
    }

    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        OrderItemRepository orderItemRepository,
                        ProductRepository productRepository) {

        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.orderBST = new OrderBST();
    }

    public OrderItem addItemToOrder(Long orderId, Long productId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (product.getStock() < quantity) {
            throw new IllegalArgumentException("Not enough product stock");
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(quantity);
        orderItem.setProduct(product);
        orderItem.setOrder(order);

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        return orderItemRepository.save(orderItem);
    }
}