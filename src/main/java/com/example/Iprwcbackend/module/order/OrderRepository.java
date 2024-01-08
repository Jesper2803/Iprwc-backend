package com.example.Iprwcbackend.module.order;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "orderItems")
    List<Order> findByUserId(Long userId);

    @EntityGraph(attributePaths = "orderItems")
    List<Order> findAll();
}
