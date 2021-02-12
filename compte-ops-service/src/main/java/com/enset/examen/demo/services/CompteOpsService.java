package com.enset.examen.demo.services;

import com.enset.examen.demo.entities.Compte;
import com.enset.examen.demo.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompteOpsService {
    public Compte addOne(Compte compte);
    public Compte virsser(Long compteCode , double montant);
    public Compte retrait(Long compteCode, double montant);
    public void virsserToCompte(Long compteCodeSource, Long compteCodeDestination,double montant);
    public Page<Operation> getOperationsByCompteId(Long idCompte, Pageable pageable);
    public Compte getCompteDetail(Long idCompte);
    public Compte changeCompteState(Long id,String etat);
}
