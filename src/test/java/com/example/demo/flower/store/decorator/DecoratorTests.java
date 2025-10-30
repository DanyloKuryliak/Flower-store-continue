package com.example.demo.flower.store.decorator;

import com.example.demo.flower.store.core.Flower;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class DecoratorTests {

    @Test
    @DisplayName("BasketDecorator should add basket to flower description and price")
    public void testBasketDecorator() {
        Flower flower = new Flower("Rose", "Red", 10.0);
        BasketDecorator decorated = new BasketDecorator(flower);
        
        assertTrue(decorated.getDescription().contains("basket"));
        assertEquals(14.0, decorated.getPrice()); // 10.0 + 4.0
        assertEquals("Rose", decorated.getName());
    }

    @Test
    @DisplayName("PaperDecorator should add paper wrapping to flower description and price")
    public void testPaperDecorator() {
        Flower flower = new Flower("Tulip", "Yellow", 8.0);
        PaperDecorator decorated = new PaperDecorator(flower);
        
        assertTrue(decorated.getDescription().contains("paper wrapping"));
        assertEquals(11.0, decorated.getPrice()); // 8.0 + 3.0
    }

    @Test
    @DisplayName("RibbonDecorator should add ribbon to flower description and price")
    public void testRibbonDecorator() {
        Flower flower = new Flower("Chamomile", "White", 5.0);
        RibbonDecorator decorated = new RibbonDecorator(flower);
        
        assertTrue(decorated.getDescription().contains("ribbon"));
        assertEquals(7.0, decorated.getPrice()); // 5.0 + 2.0
    }

    @Test
    @DisplayName("Multiple decorators can be chained together")
    public void testMultipleDecorators() {
        Flower flower = new Flower("Rose", "Red", 10.0);
        BasketDecorator withBasket = new BasketDecorator(flower);
        RibbonDecorator withRibbonAndBasket = new RibbonDecorator(withBasket);
        
        assertTrue(withRibbonAndBasket.getDescription().contains("basket"));
        assertTrue(withRibbonAndBasket.getDescription().contains("ribbon"));
        assertEquals(16.0, withRibbonAndBasket.getPrice()); // 10.0 + 4.0 + 2.0
    }

    @Test
    @DisplayName("AbstractDecorator extends from Flower")
    public void testAbstractDecoratorInheritance() {
        Flower flower = new Flower("Rose", "Red", 10.0);
        BasketDecorator decorator = new BasketDecorator(flower);
        
        assertTrue(decorator instanceof Flower);
        assertTrue(decorator instanceof AbstractDecorator);
    }
}
