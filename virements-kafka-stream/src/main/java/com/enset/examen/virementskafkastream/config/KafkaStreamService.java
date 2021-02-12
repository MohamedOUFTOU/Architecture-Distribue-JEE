package com.enset.examen.virementskafkastream.config;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaStreamService {

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kafkaStreamsConfiguration(){
        Map<String,Object> conf = new HashMap<>();
        conf.put(StreamsConfig.APPLICATION_ID_CONFIG,"Facturation-event");
        conf.put(StreamsConfig.CLIENT_ID_CONFIG,"streams-app");
        conf.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        conf.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        conf.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());
        conf.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG,1000);
        return new KafkaStreamsConfiguration(conf);
    }
}
