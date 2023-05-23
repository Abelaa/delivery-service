package com.sa.delivery.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.delivery.model.EmailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final String EMAIL_TOPIC = "email";

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void sendEmail(EmailInfo emailInfo) {
        ObjectMapper objectMapper = new ObjectMapper();
        String emailAsString = null;
        try {
            emailAsString = objectMapper.writeValueAsString(emailInfo);
            kafkaTemplate.send(EMAIL_TOPIC, emailAsString);

        } catch (JsonProcessingException e) {
            System.out.println("Unable to convert to string");
        }
    }

}
