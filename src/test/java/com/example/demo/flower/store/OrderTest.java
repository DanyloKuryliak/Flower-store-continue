package com.example.demo.flower.store;

import com.example.demo.flower.store.delivery.PostDeliveryStrategy;
import com.example.demo.flower.store.delivery.DHLDeliveryStrategy;
import com.example.demo.flower.store.payment.CreditCardPaymentStrategy;
import com.example.demo.flower.store.payment.PayPalPaymentStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import com.example.demo.flower.store.core.Order;
import com.example.demo.flower.store.core.Flower;
import com.example.demo.flower.store.core.Item;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    @DisplayName("Order should calculate total price correctly")
    public void testCalculateTotalPrice() {
        Order order = new Order(1);
        order.addItem(new Flower("Rose", "Red", 10.0));
        order.addItem(new Flower("Tulip", "Yellow", 8.0));
        
        assertEquals(18.0, order.calculateTotalPrice());
    }

    @Test
    @DisplayName("Order with credit card payment should process successfully")
    public void testOrderWithCreditCardPayment() {
        Order order = new Order(1);
        order.addItem(new Flower("Rose", "Red", 10.0));
        
        CreditCardPaymentStrategy payment = new CreditCardPaymentStrategy("1234567890123456", "12/25");
        PostDeliveryStrategy delivery = new PostDeliveryStrategy("123 Main St");
        
        order.setPaymentStrategy(payment);
        order.setDeliveryStrategy(delivery);
        
        assertDoesNotThrow(() -> order.processOrder());
        assertEquals(10.0, order.calculateTotalPrice());
    }

    @Test
    @DisplayName("Order with PayPal payment should process successfully")
    public void testOrderWithPayPalPayment() {
        Order order = new Order(1);
        order.addItem(new Flower("Rose", "Red", 10.0));
        
        PayPalPaymentStrategy payment = new PayPalPaymentStrategy("test@example.com");
        DHLDeliveryStrategy delivery = new DHLDeliveryStrategy("TRACK123", "456 Oak Ave");
        
        order.setPaymentStrategy(payment);
        order.setDeliveryStrategy(delivery);
        
        assertDoesNotThrow(() -> order.processOrder());
        assertEquals(10.0, order.calculateTotalPrice());
    }

    @Test
    @DisplayName("Order should throw exception if payment strategy is not set")
    public void testOrderWithoutPaymentStrategy() {
        Order order = new Order(1);
        order.addItem(new Flower("Rose", "Red", 10.0));
        
        PostDeliveryStrategy delivery = new PostDeliveryStrategy("123 Main St");
        order.setDeliveryStrategy(delivery);
        
        assertThrows(IllegalStateException.class, () -> order.processOrder());
    }

    @Test
    @DisplayName("Order should throw exception if delivery strategy is not set")
    public void testOrderWithoutDeliveryStrategy() {
        Order order = new Order(1);
        order.addItem(new Flower("Rose", "Red", 10.0));
        
        CreditCardPaymentStrategy payment = new CreditCardPaymentStrategy("1234567890123456", "12/25");
        order.setPaymentStrategy(payment);
        
        assertThrows(IllegalStateException.class, () -> order.processOrder());
    }

    @Test
    @DisplayName("Order should store user ID correctly")
    public void testOrderUserId() {
        Order order = new Order(42);
        assertEquals(42, order.getUserId());
    }

    @Test
    @DisplayName("Order should return items list")
    public void testOrderGetItems() {
        Order order = new Order(1);
        order.addItem(new Flower("Rose", "Red", 10.0));
        order.addItem(new Flower("Tulip", "Yellow", 8.0));
        
        List<Item> items = order.getItems();
        assertEquals(2, items.size());
    }
}
