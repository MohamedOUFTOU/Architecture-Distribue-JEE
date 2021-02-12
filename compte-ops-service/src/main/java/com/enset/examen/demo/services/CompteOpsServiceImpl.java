package com.enset.examen.demo.services;

import com.enset.examen.demo.entities.Compte;
import com.enset.examen.demo.entities.Operation;
import com.enset.examen.demo.feign.ClientConsumer;
import com.enset.examen.demo.repositories.CompteRepository;
import com.enset.examen.demo.repositories.OperationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class CompteOpsServiceImpl implements CompteOpsService {

    private CompteRepository compteRepository;
    private OperationRepository operationRepository;
    private ClientConsumer consumer;

    public CompteOpsServiceImpl(CompteRepository compteRepository, OperationRepository operationRepository,ClientConsumer consumer) {
        this.consumer = consumer;
        this.compteRepository = compteRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    public Compte addOne(Compte compte) {
        return compteRepository.save(compte);
    }

    @Override
    public Compte virsser(Long compteCode, double montant) {
        Compte compte = compteRepository.findByCode(compteCode);
        compte.setSolde(compte.getSolde() + montant);
        Operation operation = operationRepository.save(new Operation(null,Integer.toUnsignedLong(compte.getOperations().size()+1),new Date(),montant,"DEBIT",compte));
        compte.getOperations().add(
          operation
        );
        return compteRepository.save(compte);
    }

    @Override
    public Compte retrait(Long compteCode, double montant) {
        Compte compte = compteRepository.findByCode(compteCode);
        compte.setSolde(compte.getSolde() - montant);
        Operation operation = operationRepository.save(new Operation(null,Integer.toUnsignedLong(compte.getOperations().size()+1),new Date(),montant,"CREDIT",compte));
        compte.getOperations().add(
                operation
        );
        return compteRepository.save(compte);
    }

    @Override
    public void virsserToCompte(Long compteCodeSource, Long compteCodeDestination, double montant) {
        retrait(compteCodeSource,montant);
        virsser(compteCodeDestination,montant);
    }

    @Override
    public Page<Operation> getOperationsByCompteId(Long idCompte, Pageable pageable) {
        return operationRepository.findAllByCompteId(idCompte,pageable);
    }

    @Override
    public Compte getCompteDetail(Long idCompte) {
        Compte compte = compteRepository.findById(idCompte).get();
        return compte;
    }

    @Override
    public Compte changeCompteState(Long id, String etat) {
        Compte compte = compteRepository.getOne(id);
        compte.setEtat(etat);
        return compteRepository.save(compte);
    }
}
