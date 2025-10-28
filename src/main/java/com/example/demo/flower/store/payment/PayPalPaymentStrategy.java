package com.example.demo.flower.store.payment;

public class PayPalPaymentStrategy implements Payment {
    private String email;

    public PayPalPaymentStrategy(String email) {
        this.email = email;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
        System.out.println("Email: " + email);
        return true;
    }

    public String getEmail() {
        return email;
    }
}
