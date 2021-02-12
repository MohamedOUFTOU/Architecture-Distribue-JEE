package com.enset.examen.virementskafkastream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class VirementsKafkaStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirementsKafkaStreamApplication.class, args);
    }

}
