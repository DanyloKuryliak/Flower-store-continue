package com.example.demo.flower.store.service;

import com.example.demo.flower.store.core.Flower;
import com.example.demo.flower.store.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlowerService {

    @Autowired
    private FlowerRepository flowerRepository;

    public List<Flower> getAllFlowers() {
        return flowerRepository.findAll();
    }

    public Flower addFlower(Flower flower) {
        return flowerRepository.save(flower);
    }

    public Optional<Flower> getFlowerById(Long id) {
        return flowerRepository.findById(id);
    }

    public void deleteFlower(Long id) {
        flowerRepository.deleteById(id);
    }
}
