package com.example.demo.flower.store.decorator;

import com.example.demo.flower.store.Flower;

public class PaperDecorator extends AbstractDecorator {
    private final double paperPrice = 3.0;

    public PaperDecorator(Flower flower) {
        super(flower);
    }

    @Override
    public String getDescription() {
        return flower.getDescription() + " with paper wrapping";
    }

    @Override
    public double getPrice() {
        return flower.getPrice() + paperPrice;
    }
}
