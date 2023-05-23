package com.sa.delivery.controller;

import com.sa.delivery.model.EmailInfo;
import com.sa.delivery.model.Order;
import com.sa.delivery.service.DeliverService;
import com.sa.delivery.model.Delivery;
import com.sa.delivery.service.KafkaService;
import com.sa.delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/deliveries")
public class DeliveryController {

    @Autowired
    private DeliverService deliverService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private KafkaService kafkaService;

    @PostMapping
    public Delivery create(@RequestBody Delivery delivery) {
        deliverService.create(delivery);
        return delivery;
    }

    @PutMapping("/{id}")
    public Delivery update(@PathVariable String id, @RequestParam String status) {
        Delivery delivery = deliverService.getById(id).orElseThrow();
        delivery.setStatus(status);
        deliverService.update(delivery);

        String orderId = delivery.getOrderId();
        Order order = orderService.getOrderById(orderId);

        String message = "delivery status for " + orderId + " -> " + status;
        EmailInfo emailInfo = new EmailInfo(order.getUserEmail(), message);
        kafkaService.sendEmail(emailInfo);

        return delivery;
    }

    @GetMapping
    public List<Delivery> getAll() {
        return deliverService.getAll();
    }

    @GetMapping("/{id}")
    public Delivery getById(@PathVariable String id) {
        return deliverService.getById(id).orElseThrow();
    }

}
