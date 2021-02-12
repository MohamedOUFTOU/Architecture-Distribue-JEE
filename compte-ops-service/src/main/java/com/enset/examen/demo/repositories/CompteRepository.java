package com.enset.examen.demo.repositories;

import com.enset.examen.demo.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CompteRepository extends JpaRepository<Compte,Long> {
    Compte findByCode(Long code);
}
