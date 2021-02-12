package com.enset.examen.demo.entities;

import com.enset.examen.demo.DTO.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Compte {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long code;
    private double solde;
    @CreatedDate
    private Date dateCreation;
    private String type;
    private String etat;

    @OneToMany(mappedBy = "compte")
    private Collection<Operation> operations = new ArrayList<>();

    private Long clientID;

    @Transient
    private Client client;
}
