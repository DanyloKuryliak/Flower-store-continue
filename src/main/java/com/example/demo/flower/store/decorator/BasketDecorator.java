package com.example.demo.flower.store.decorator;

import com.example.demo.flower.store.Flower;

public class BasketDecorator extends AbstractDecorator {
    private final double basketPrice = 4.0;

    public BasketDecorator(Flower flower) {
        super(flower);
    }

    @Override
    public String getDescription() {
        return flower.getDescription() + " in a basket";
    }

    @Override
    public double getPrice() {
        return flower.getPrice() + basketPrice;
    }
}
