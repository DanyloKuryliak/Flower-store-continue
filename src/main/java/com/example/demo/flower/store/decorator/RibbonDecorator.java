package com.example.demo.flower.store.decorator;

import com.example.demo.flower.store.core.Flower;

public class RibbonDecorator extends AbstractDecorator {
    private final double ribbonPrice = 2.0;

    public RibbonDecorator(Flower flower) {
        super(flower);
    }

    @Override
    public String getDescription() {
        return flower.getDescription() + " with ribbon";
    }

    @Override
    public double getPrice() {
        return flower.getPrice() + ribbonPrice;
    }
}
