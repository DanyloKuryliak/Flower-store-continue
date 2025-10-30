package com.example.demo.flower.store.delivery;

import com.example.demo.flower.store.core.Item;

import java.util.List;

public interface Delivery {
    void deliver(List<Item> items);
}
