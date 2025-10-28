package com.example.demo.flower.store.api;

import com.example.demo.flower.store.Order;
import com.example.demo.flower.store.delivery.Delivery;
import com.example.demo.flower.store.delivery.DHLDeliveryStrategy;
import com.example.demo.flower.store.delivery.PostDeliveryStrategy;
import com.example.demo.flower.store.payment.Payment;
import com.example.demo.flower.store.payment.CreditCardPaymentStrategy;
import com.example.demo.flower.store.payment.PayPalPaymentStrategy;
import com.example.demo.flower.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public String createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable int orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/{orderId}/process")
    public String processOrder(@PathVariable int orderId) {
        return orderService.processOrder(orderId);
    }

    @PostMapping("/demo")
    public String createDemoOrder(@RequestBody Map<String, String> params) {
        String paymentType = params.getOrDefault("paymentType", "creditcard");
        String deliveryType = params.getOrDefault("deliveryType", "post");
        String address = params.getOrDefault("address", "123 Main St");
        String email = params.getOrDefault("email", "user@example.com");
        String cardNumber = params.getOrDefault("cardNumber", "1234567890123456");

        Order order = new Order(1);
        
        // Add some items
        order.addItem(new com.example.demo.flower.store.Flower("Rose", "Red", 15.0));
        order.addItem(new com.example.demo.flower.store.Flower("Tulip", "Yellow", 12.0));

        Payment payment;
        if (paymentType.equalsIgnoreCase("paypal")) {
            payment = new PayPalPaymentStrategy(email);
        } else {
            payment = new CreditCardPaymentStrategy(cardNumber, "12/25");
        }
        order.setPaymentStrategy(payment);

        Delivery delivery;
        if (deliveryType.equalsIgnoreCase("dhl")) {
            delivery = new DHLDeliveryStrategy("TRACK" + System.currentTimeMillis(), address);
        } else {
            delivery = new PostDeliveryStrategy(address);
        }
        order.setDeliveryStrategy(delivery);

        orderService.addOrder(order);
        order.processOrder();

        return "Order processed successfully! Total: $" + order.calculateTotalPrice();
    }

    public static class OrderRequest {
        private List<ItemRequest> items;
        private String paymentType;
        private String deliveryType;
        private String address;
        private String email;
        private String cardNumber;
        private int userId;

        // Getters and setters
        public List<ItemRequest> getItems() { return items; }
        public void setItems(List<ItemRequest> items) { this.items = items; }
        public String getPaymentType() { return paymentType; }
        public void setPaymentType(String paymentType) { this.paymentType = paymentType; }
        public String getDeliveryType() { return deliveryType; }
        public void setDeliveryType(String deliveryType) { this.deliveryType = deliveryType; }
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getCardNumber() { return cardNumber; }
        public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
        public int getUserId() { return userId; }
        public void setUserId(int userId) { this.userId = userId; }
    }

    public static class ItemRequest {
        private String name;
        private String color;
        private double price;

        // Getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getColor() { return color; }
        public void setColor(String color) { this.color = color; }
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
    }
}
