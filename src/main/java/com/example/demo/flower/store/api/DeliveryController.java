package com.example.demo.flower.store.api;

import com.example.demo.flower.store.delivery.DHLDeliveryStrategy;
import com.example.demo.flower.store.delivery.PostDeliveryStrategy;
import com.example.demo.flower.store.Flower;
import com.example.demo.flower.store.Item;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    @PostMapping("/post")
    public Map<String, Object> processPostDelivery(@RequestBody Map<String, Object> request) {
        String address = (String) request.get("address");
        List<Map<String, Object>> itemsData = (List<Map<String, Object>>) request.get("items");

        List<Item> items = new ArrayList<>();
        for (Map<String, Object> data : itemsData) {
            items.add(new Flower(
                (String) data.get("name"),
                (String) data.get("color"),
                Double.parseDouble(data.get("price").toString())
            ));
        }

        PostDeliveryStrategy delivery = new PostDeliveryStrategy(address);
        delivery.deliver(items);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("deliveryType", "Post");
        response.put("address", address);
        response.put("itemsCount", items.size());
        return response;
    }

    @PostMapping("/dhl")
    public Map<String, Object> processDHLDelivery(@RequestBody Map<String, Object> request) {
        String trackingNumber = (String) request.getOrDefault("trackingNumber", "DHL" + System.currentTimeMillis());
        String address = (String) request.get("address");
        List<Map<String, Object>> itemsData = (List<Map<String, Object>>) request.get("items");

        List<Item> items = new ArrayList<>();
        for (Map<String, Object> data : itemsData) {
            items.add(new Flower(
                (String) data.get("name"),
                (String) data.get("color"),
                Double.parseDouble(data.get("price").toString())
            ));
        }

        DHLDeliveryStrategy delivery = new DHLDeliveryStrategy(trackingNumber, address);
        delivery.deliver(items);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("deliveryType", "DHL");
        response.put("trackingNumber", trackingNumber);
        response.put("address", address);
        response.put("itemsCount", items.size());
        return response;
    }
}
