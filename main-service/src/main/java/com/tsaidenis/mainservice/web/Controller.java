package com.tsaidenis.mainservice.web;

import com.tsaidenis.mainservice.kafka.KafkaProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final KafkaProducer kafkaProducer;

    public Controller(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/kafka/send")
    public String sendMessage(@RequestBody String message) {
        kafkaProducer.sendMessage(message);
        return "Successfully sent message: ";
    }
}
