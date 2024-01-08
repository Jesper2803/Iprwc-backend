package com.example.Iprwcbackend.module.order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderResponse {
    private Long orderId;
    private Long userId;
    private double totalPrice;
    private String formattedDate;
    private OrderStatus processed;
    private List<OrderItem> orderItems = new ArrayList<>();

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public OrderStatus getProcessed() {
        return processed;
    }

    public void setProcessed(OrderStatus processed) {
        this.processed = processed;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderResponse(Order order) {
        this.orderId = order.getOrderId();
        this.userId = order.getUserId();
        this.totalPrice = order.getTotalPrice();
        this.formattedDate = formatLocalDateTime(order.getDate());
        this.processed = order.getProcessed();
        this.orderItems = order.getOrderItems();
    }

    private String formatLocalDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}
