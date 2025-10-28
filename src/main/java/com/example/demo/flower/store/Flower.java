package com.example.demo.flower.store;

import jakarta.persistence.*;

@Entity
@Table(name = "flowers")
public class Flower extends Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private double sepalLength;
    
    @Enumerated(EnumType.STRING)
    private FlowerColor color;
    
    private double price;
    
    @Enumerated(EnumType.STRING)
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
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return name + " (" + getColor() + ")";
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
    public void setId(Long id) {
        this.id = id;
    }
    
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
