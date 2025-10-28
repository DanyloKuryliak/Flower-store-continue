package com.example.demo.flower.store;

public class Flower extends Item {
    private String name;
    private double sepalLength;
    private FlowerColor color;
    private double price;
    private FlowerType flowerType;

    public Flower() {
        this.name = "Rose";
        this.sepalLength = 0;
        this.color = FlowerColor.RED;
        this.price = 0;
        this.flowerType = FlowerType.ROSE;
    }

    public Flower(String name, String color, double price) {
        this.name = name;
        this.sepalLength = 0;
        this.color = FlowerColor.RED;
        this.price = price;
        this.flowerType = FlowerType.ROSE;
    }

    public Flower(Flower flower) {
        this.name = flower.name;
        this.sepalLength = flower.sepalLength;
        this.color = flower.color;
        this.price = flower.price;
        this.flowerType = flower.flowerType;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getSepalLength() {
        return sepalLength;
    }

    public double getPrice() {
        return this.price;
    }

    public FlowerType getFlowerType() {
        return flowerType;
    }

    public String getColor() {
        return color.toString();
    }

    public FlowerColor getFlowerColor() {
        return color;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSepalLength(double newSepalLength) {
        this.sepalLength = newSepalLength;
    }

    public void setColor(FlowerColor newColor) {
        this.color = newColor;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public void setFlowerType(FlowerType newFlowerType) {
        this.flowerType = newFlowerType;
    }
}
