package com.example.demo.flower.store.api;

import com.example.demo.flower.store.core.Flower;
import com.example.demo.flower.store.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flowers")
public class FlowerController {

    @Autowired
    private FlowerService flowerService;

    @GetMapping
    public List<Flower> getAllFlowers() {
        return flowerService.getAllFlowers();
    }

    @PostMapping
    public Flower addFlower(@RequestBody Flower flower) {
        return flowerService.addFlower(flower);
    }
}
