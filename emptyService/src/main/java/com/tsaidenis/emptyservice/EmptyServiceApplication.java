package com.tsaidenis.emptyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class EmptyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmptyServiceApplication.class, args);
    }

}
