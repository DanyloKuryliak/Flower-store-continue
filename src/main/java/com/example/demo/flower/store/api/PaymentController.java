package com.example.demo.flower.store.api;

import com.example.demo.flower.store.payment.CreditCardPaymentStrategy;
import com.example.demo.flower.store.payment.PayPalPaymentStrategy;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @PostMapping("/creditcard")
    public Map<String, Object> processCreditCard(@RequestBody Map<String, String> request) {
        String cardNumber = request.get("cardNumber");
        String expiryDate = request.getOrDefault("expiryDate", "12/25");
        double amount = Double.parseDouble(request.getOrDefault("amount", "0.0"));

        CreditCardPaymentStrategy payment = new CreditCardPaymentStrategy(cardNumber, expiryDate);
        boolean success = payment.pay(amount);

        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("paymentType", "CreditCard");
        response.put("cardNumber", payment.getCardNumber());
        response.put("amount", amount);
        return response;
    }

    @PostMapping("/paypal")
    public Map<String, Object> processPayPal(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        double amount = Double.parseDouble(request.getOrDefault("amount", "0.0"));

        PayPalPaymentStrategy payment = new PayPalPaymentStrategy(email);
        boolean success = payment.pay(amount);

        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("paymentType", "PayPal");
        response.put("email", email);
        response.put("amount", amount);
        return response;
    }
}
