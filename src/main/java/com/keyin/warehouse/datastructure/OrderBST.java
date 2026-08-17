package com.keyin.warehouse.datastructure;

import com.keyin.warehouse.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderBST {

    private OrderNode root;

    public void insert(Order order) {
        root = insertRecursive(root, order);
    }

    private OrderNode insertRecursive(OrderNode current, Order order) {
        if (current == null) {
            return new OrderNode(order);
        }

        if (order.getPriorityLevel() < current.data.getPriorityLevel()) {
            current.left = insertRecursive(current.left, order);
        } else {
            // Equal or higher priority goes right
            current.right = insertRecursive(current.right, order);
        }

        return current;
    }

    public List<Order> inorder() {
        List<Order> orders = new ArrayList<>();

        inorderRecursive(root, orders);

        return orders;
    }

    private void inorderRecursive(OrderNode node, List<Order> orders) {
        if (node == null) {
            return;
        }

        inorderRecursive(node.left, orders);
        orders.add(node.data);
        inorderRecursive(node.right, orders);
    }

    public Order findHighest() {
        if (root == null) {
            return null;
        }

        OrderNode current = root;

        while (current.right != null) {
            current = current.right;
        }

        return current.data;
    }

    public Order findLowest() {
        if (root == null) {
            return null;
        }

        OrderNode current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current.data;
    }
}