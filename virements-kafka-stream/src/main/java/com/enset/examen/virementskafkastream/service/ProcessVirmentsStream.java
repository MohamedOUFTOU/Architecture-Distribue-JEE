package com.enset.examen.virementskafkastream.service;


import com.enset.examen.virementskafkastream.DTO.Operation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessVirmentsStream {
    @Autowired
    public void process(final StreamsBuilder builder){


        KStream<String,Long> counts = builder.stream("VIRMENTSTOPIC", Consumed.with(Serdes.String(),Serdes.String()))
                .map((k,v) -> new KeyValue<>(k,jsonToOperation(v).getId()+""))
                .groupBy((key, value) -> key)
                .count().toStream();

        counts.foreach((k,v) -> {
            System.out.println(k + " -> "+ v);
        });
        counts.to("results-analysis-virement", Produced.with(Serdes.String(),Serdes.Long()));
    }

    private Operation jsonToOperation(String value){
        Gson gson = new Gson();
        Operation operation = new Operation();
        try {
            operation = gson.fromJson(value,Operation.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return operation;
    }
}
