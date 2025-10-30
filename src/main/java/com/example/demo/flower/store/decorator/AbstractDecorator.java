package com.example.demo.flower.store.decorator;

import com.example.demo.flower.store.core.Flower;
import com.example.demo.flower.store.core.FlowerType;

public abstract class AbstractDecorator extends Flower {
    protected Flower flower;

    public AbstractDecorator(Flower flower) {
        super();
        this.flower = flower;
    }

    @Override
    public String getName() {
        return flower.getName();
    }

    @Override
    public String getDescription() {
        return flower.getDescription();
    }

    @Override
    public double getPrice() {
        return flower.getPrice();
    }

    @Override
    public FlowerType getFlowerType() {
        return flower.getFlowerType();
    }
}
