package com.keyin.warehouse.repository;

import com.keyin.warehouse.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}