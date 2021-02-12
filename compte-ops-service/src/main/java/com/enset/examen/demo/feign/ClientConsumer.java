package com.enset.examen.demo.feign;

import com.enset.examen.demo.DTO.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CLIENT-SERVICE")
public interface ClientConsumer {

    @GetMapping("/clients/{id}")
    Client findClientById(@PathVariable("id") Long id);
}
