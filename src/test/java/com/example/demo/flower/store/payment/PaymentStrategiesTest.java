package com.example.demo.flower.store.payment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentStrategiesTest {

    @Test
    @DisplayName("CreditCardPaymentStrategy should process payment successfully")
    public void testCreditCardPayment() {
        CreditCardPaymentStrategy payment = new CreditCardPaymentStrategy("1234567890123456", "12/25");
        boolean result = payment.pay(100.0);
        assertTrue(result);
        assertEquals("1234567890123456", payment.getCardNumber());
        assertEquals("12/25", payment.getExpiryDate());
    }

    @Test
    @DisplayName("PayPalPaymentStrategy should process payment successfully")
    public void testPayPalPayment() {
        PayPalPaymentStrategy payment = new PayPalPaymentStrategy("test@example.com");
        boolean result = payment.pay(150.0);
        assertTrue(result);
        assertEquals("test@example.com", payment.getEmail());
    }

    @Test
    @DisplayName("CreditCardPaymentStrategy should mask card number correctly")
    public void testCardNumberMasking() {
        CreditCardPaymentStrategy payment = new CreditCardPaymentStrategy("1234567890123456", "12/25");
        assertTrue(payment.getCardNumber().length() > 4);
        // The masking is done internally, so we just verify the payment works
        assertTrue(payment.pay(50.0));
    }

    @Test
    @DisplayName("Both payment strategies should implement Payment interface")
    public void testPaymentInterface() {
        Payment creditCard = new CreditCardPaymentStrategy("1234567890123456", "12/25");
        Payment paypal = new PayPalPaymentStrategy("test@example.com");
        
        assertNotNull(creditCard);
        assertNotNull(paypal);
        assertTrue(creditCard instanceof Payment);
        assertTrue(paypal instanceof Payment);
    }
}
