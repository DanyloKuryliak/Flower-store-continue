package com.example.demo.flower.store.delivery;

import com.example.demo.flower.store.Flower;
import com.example.demo.flower.store.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryStrategiesTest {

    @Test
    @DisplayName("PostDeliveryStrategy should deliver items")
    public void testPostDelivery() {
        List<Item> items = Arrays.asList(
            new Flower("Rose", "Red", 10.0),
            new Flower("Tulip", "Yellow", 8.0)
        );
        
        PostDeliveryStrategy delivery = new PostDeliveryStrategy("123 Main St, City");
        assertNotNull(delivery);
        assertEquals("123 Main St, City", delivery.getRecipientAddress());
        delivery.deliver(items);
    }

    @Test
    @DisplayName("DHLDeliveryStrategy should deliver items")
    public void testDHLDelivery() {
        List<Item> items = Arrays.asList(
            new Flower("Rose", "Red", 10.0),
            new Flower("Chamomile", "White", 7.0)
        );
        
        DHLDeliveryStrategy delivery = new DHLDeliveryStrategy("TRACK123", "456 Oak Ave, Town");
        assertNotNull(delivery);
        assertEquals("TRACK123", delivery.getTrackingNumber());
        assertEquals("456 Oak Ave, Town", delivery.getRecipientAddress());
        delivery.deliver(items);
    }

    @Test
    @DisplayName("Both delivery strategies should implement Delivery interface")
    public void testDeliveryInterface() {
        Delivery post = new PostDeliveryStrategy("123 Main St");
        Delivery dhl = new DHLDeliveryStrategy("TRACK123", "456 Oak Ave");
        
        assertNotNull(post);
        assertNotNull(dhl);
        assertTrue(post instanceof Delivery);
        assertTrue(dhl instanceof Delivery);
    }

    @Test
    @DisplayName("DHLDeliveryStrategy should have tracking number")
    public void testDHLTrackingNumber() {
        String trackingNumber = "DHL" + System.currentTimeMillis();
        DHLDeliveryStrategy delivery = new DHLDeliveryStrategy(trackingNumber, "123 Main St");
        assertEquals(trackingNumber, delivery.getTrackingNumber());
    }

    @Test
    @DisplayName("PostDeliveryStrategy should handle empty items list")
    public void testPostDeliveryWithEmptyList() {
        PostDeliveryStrategy delivery = new PostDeliveryStrategy("123 Main St");
        delivery.deliver(Arrays.asList());
        // Should not throw exception
        assertTrue(true);
    }
}
