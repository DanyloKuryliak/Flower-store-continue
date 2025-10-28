package com.example.demo.flower.store.api;

import com.example.demo.flower.store.Flower;
import com.example.demo.flower.store.decorator.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/decorator")
public class DecoratorController {

    @PostMapping("/basket")
    public Flower decorateWithBasket(@RequestBody Flower flower) {
        return new BasketDecorator(flower);
    }

    @PostMapping("/paper")
    public Flower decorateWithPaper(@RequestBody Flower flower) {
        return new PaperDecorator(flower);
    }

    @PostMapping("/ribbon")
    public Flower decorateWithRibbon(@RequestBody Flower flower) {
        return new RibbonDecorator(flower);
    }

    @PostMapping("/basket-and-ribbon")
    public Flower decorateWithBasketAndRibbon(@RequestBody Flower flower) {
        return new RibbonDecorator(new BasketDecorator(flower));
    }

    @PostMapping("/all")
    public Flower decorateWithAll(@RequestBody Flower flower) {
        return new RibbonDecorator(new PaperDecorator(new BasketDecorator(flower)));
    }
}
