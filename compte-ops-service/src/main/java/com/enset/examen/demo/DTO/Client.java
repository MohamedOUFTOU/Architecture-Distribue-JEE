package com.enset.examen.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Client implements Serializable {
    private Long code;
    private String nom;
    private String email;
}
