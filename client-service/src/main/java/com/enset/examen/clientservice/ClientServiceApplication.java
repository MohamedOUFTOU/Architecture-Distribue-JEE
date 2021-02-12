package com.enset.examen.clientservice;

import com.enset.examen.clientservice.entities.Client;
import com.enset.examen.clientservice.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class ClientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(RepositoryRestConfiguration repositoryRestConfiguration, ClientRepository clientRepository){
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Client.class);
            clientRepository.save(new Client(null,1L,"Mohamed OUFTOU","mohamed@gmail.com"));
        };
    }
}
