package com.enset.examen.demo.repositories;

import com.enset.examen.demo.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OperationRepository extends JpaRepository<Operation,Long> {
    Page<Operation> findAllByCompteId(Long id, Pageable pageable);
}
