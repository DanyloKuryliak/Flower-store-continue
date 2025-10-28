package com.example.demo.flower.store.api;

import com.example.demo.flower.store.Flower;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/flowers")
public class FlowerController {

    @GetMapping
    public List<Flower> getAllFlowers() {
        return List.of(
                new Flower("Rose", "Red", 5.0),
                new Flower("Tulip", "Yellow", 4.0),
                new Flower("Chamomile", "White", 3.5)
        );
    }
}
