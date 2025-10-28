package com.example.demo.flower.store.payment;

public class CreditCardPaymentStrategy implements Payment {
    private String cardNumber;
    private String expiryDate;

    public CreditCardPaymentStrategy(String cardNumber, String expiryDate) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
        System.out.println("Card: " + maskCardNumber(cardNumber));
        return true;
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber.length() > 4) {
            return "****" + cardNumber.substring(cardNumber.length() - 4);
        }
        return "****";
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}
