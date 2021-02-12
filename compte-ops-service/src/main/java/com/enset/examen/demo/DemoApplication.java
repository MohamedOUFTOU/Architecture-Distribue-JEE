package com.enset.examen.demo;

import com.enset.examen.demo.entities.Compte;
import com.enset.examen.demo.entities.Operation;
import com.enset.examen.demo.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
@EnableKafka
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner start(RepositoryRestConfiguration repositoryRestConfiguration, CompteRepository compteRepository){
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Compte.class, Operation.class);
            compteRepository.save(new Compte(null,1L,2000,new Date(),"COURANT","ACTIVE",null,1L,null));
            compteRepository.save(new Compte(null,2L,3500,new Date(),"COURANT","ACTIVE",null,1L,null));
        };
    }

}
