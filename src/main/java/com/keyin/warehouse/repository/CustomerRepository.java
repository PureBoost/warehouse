package com.keyin.warehouse.repository;

import com.keyin.warehouse.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}