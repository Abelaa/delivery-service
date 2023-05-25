package com.sa.delivery.service;

import com.sa.delivery.repository.DeliveryRepository;
import com.sa.delivery.model.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliverService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public void create(Delivery delivery) {
        deliveryRepository.save(delivery);
    }

    public void update(Delivery delivery) {
        deliveryRepository.save(delivery);
    }

    public List<Delivery> getAll() {
        return deliveryRepository.findAll();
    }

    public Optional<Delivery> getById(String id) {
        return deliveryRepository.findById(id);
    }

}
