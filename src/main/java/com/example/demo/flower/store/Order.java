package com.example.demo.flower.store;

import com.example.demo.flower.store.payment.Payment;
import com.example.demo.flower.store.delivery.Delivery;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Item> items;
    private Payment payment;
    private Delivery delivery;
    private int userId;

    public Order(List<Item> items, Payment payment, Delivery delivery, int userId) {
        this.items = new ArrayList<>(items);
        this.payment = payment;
        this.delivery = delivery;
        this.userId = userId;
    }

    public Order(int userId) {
        this.items = new ArrayList<>();
        this.userId = userId;
        this.payment = null;
        this.delivery = null;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void setPaymentStrategy(Payment payment) {
        this.payment = payment;
    }

    public void setDeliveryStrategy(Delivery delivery) {
        this.delivery = delivery;
    }

    public double calculateTotalPrice() {
        return items.stream()
                .mapToDouble(Item::getPrice)
                .sum();
    }

    public void processOrder() {
        if (payment == null || delivery == null) {
            throw new IllegalStateException("Payment and delivery strategies must be set before processing order");
        }
        double totalPrice = calculateTotalPrice();
        payment.pay(totalPrice);
        delivery.deliver(items);
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public Payment getPayment() {
        return payment;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public int getUserId() {
        return userId;
    }
}
