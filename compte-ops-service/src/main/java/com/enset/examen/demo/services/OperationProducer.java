package com.enset.examen.demo.services;

import com.enset.examen.demo.entities.Operation;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class OperationProducer implements ApplicationRunner {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        AtomicReference<Long> cnt = new AtomicReference<>(0L);
        System.out.println("---------------------------------------");
        List<String> client_name = Arrays.asList("Compte 10","Compte 30","Compte 40");
        List<String> type_ops = Arrays.asList("DEBIT" , "CREDIT");
        Random random = new Random();
        Gson gson = new Gson();

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(()->{
            cnt.set(cnt.get() + 1);

            String type = type_ops.get(random.nextInt(type_ops.size()));
            String client = client_name.get(random.nextInt(client_name.size()));
            Operation operation = new Operation(cnt.get(), cnt.get(), new Date(), random.nextDouble()*20000, type,null);


            kafkaTemplate.send("VIRMENTSTOPIC",client,gson.toJson(operation));
            log.info("[SENDING]: key => "+client + "  value => "+gson.toJson(operation));
        },0,1000, TimeUnit.MILLISECONDS);
    }
}
