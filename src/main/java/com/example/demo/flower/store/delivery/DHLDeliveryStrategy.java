package com.example.demo.flower.store.delivery;

import com.example.demo.flower.store.core.Item;

import java.util.List;

public class DHLDeliveryStrategy implements Delivery {
    private String trackingNumber;
    private String recipientAddress;

    public DHLDeliveryStrategy(String trackingNumber, String recipientAddress) {
        this.trackingNumber = trackingNumber;
        this.recipientAddress = recipientAddress;
    }

    @Override
    public void deliver(List<Item> items) {
        System.out.println("Delivering via DHL");
        System.out.println("Tracking Number: " + trackingNumber);
        System.out.println("Address: " + recipientAddress);
        System.out.println("Items to deliver: " + items.size());
        for (Item item : items) {
            System.out.println("  - " + item.getClass().getSimpleName());
        }
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }
}
