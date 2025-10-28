package com.example.demo.flower.store.delivery;

import com.example.demo.flower.store.Item;

import java.util.List;

public class PostDeliveryStrategy implements Delivery {
    private String recipientAddress;

    public PostDeliveryStrategy(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    @Override
    public void deliver(List<Item> items) {
        System.out.println("Delivering via Post to: " + recipientAddress);
        System.out.println("Items to deliver: " + items.size());
        for (Item item : items) {
            System.out.println("  - " + item.getClass().getSimpleName());
        }
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }
}
