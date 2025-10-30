package com.example.demo.flower.store.core;

public class FlowerPack extends Item {
    private Flower flower;
    private int amount;

    public FlowerPack(Flower flower, int amount) {
        this.flower = new Flower(flower);
        this.amount = amount;
    }

    // Getters
    public Flower getFlower() {
        return flower;
    }

    public int getAmount() {
        return amount;
    }

    // Setters
    public void setFlower(Flower newFlower) {
        this.flower = newFlower;
    }

    public void setAmount(int newAmount) {
        this.amount = newAmount;
    }

    @Override
    public double getPrice() {
        return flower.getPrice() * amount;
    }
}