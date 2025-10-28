package com.example.demo.flower.store;
import java.util.ArrayList;
import java.util.List;

public class FlowerBucket extends Item {
    private List<FlowerPack> flowerPacks = new ArrayList<>();

    public FlowerBucket() {
        this.flowerPacks = new ArrayList<>();
    }

    public void addFlowerPack(FlowerPack flowerPack) {
        flowerPacks.add(flowerPack);
    }

    public List<FlowerPack> getFlowerPacks() {
        return new ArrayList<>(flowerPacks);
    }

    @Override
    public double getPrice() {
        return flowerPacks.stream()
                .mapToDouble(FlowerPack::getPrice)
                .sum();
    }
}
