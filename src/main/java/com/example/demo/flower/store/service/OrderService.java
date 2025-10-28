package com.example.demo.flower.store.service;

import com.example.demo.flower.store.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private List<Order> orders = new ArrayList<>();
    private int nextOrderId = 1;

    public String createOrder(com.example.demo.flower.store.api.OrderController.OrderRequest request) {
        Order order = new Order(request.getUserId());
        
        // Add items
        for (var itemReq : request.getItems()) {
            order.addItem(new com.example.demo.flower.store.Flower(
                itemReq.getName(), 
                itemReq.getColor(), 
                itemReq.getPrice()
            ));
        }

        // Set payment strategy
        com.example.demo.flower.store.payment.Payment payment;
        if ("paypal".equalsIgnoreCase(request.getPaymentType())) {
            payment = new com.example.demo.flower.store.payment.PayPalPaymentStrategy(request.getEmail());
        } else {
            payment = new com.example.demo.flower.store.payment.CreditCardPaymentStrategy(
                request.getCardNumber(), "12/25"
            );
        }
        order.setPaymentStrategy(payment);

        // Set delivery strategy
        com.example.demo.flower.store.delivery.Delivery delivery;
        if ("dhl".equalsIgnoreCase(request.getDeliveryType())) {
            delivery = new com.example.demo.flower.store.delivery.DHLDeliveryStrategy(
                "TRACK" + System.currentTimeMillis(), 
                request.getAddress()
            );
        } else {
            delivery = new com.example.demo.flower.store.delivery.PostDeliveryStrategy(request.getAddress());
        }
        order.setDeliveryStrategy(delivery);

        orders.add(order);
        return "Order created successfully with ID: " + (orders.size() - 1);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public Order getOrderById(int orderId) {
        if (orderId >= 0 && orderId < orders.size()) {
            return orders.get(orderId);
        }
        throw new IllegalArgumentException("Order not found with ID: " + orderId);
    }

    public String processOrder(int orderId) {
        Order order = getOrderById(orderId);
        order.processOrder();
        return "Order processed successfully! Total: $" + order.calculateTotalPrice();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
