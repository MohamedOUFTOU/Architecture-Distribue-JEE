package com.enset.examen.demo.Controller;

import com.enset.examen.demo.entities.Compte;
import com.enset.examen.demo.entities.Operation;
import com.enset.examen.demo.feign.ClientConsumer;
import com.enset.examen.demo.services.CompteOpsService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompteOpsController {

    private CompteOpsService compteOpsService;
    private ClientConsumer consumer;

    public CompteOpsController(CompteOpsService compteOpsService,ClientConsumer consumer) {
        this.compteOpsService = compteOpsService;
        this.consumer = consumer;
    }

    @PostMapping("/addCompte")
    public Compte addCompte(@RequestBody Compte compte){
        return  compteOpsService.addOne(compte);
    }

    @PostMapping("/versserCompte")
    public Compte virsser(@RequestBody Virement virement){
        return  compteOpsService.virsser(virement.getCompteCode(),virement.getMontant());
    }

    @PostMapping("/retraitCompte")
    public Compte retrait(@RequestBody Virement virement){
        return  compteOpsService.retrait(virement.getCompteCode(),virement.getMontant());
    }

    @PostMapping("/viremntToCompte")
    public void virsserToCompte(@RequestBody VirementToCompte virement){
        compteOpsService.virsserToCompte(virement.getCompteCodeSource(),virement.getCompteCodeDestination(),virement.getMontant());
    }

    @GetMapping("/getOperation/{id}")
    public Page<Operation> getOperations(@PathVariable("id") Long id, Pageable pageable){
        return compteOpsService.getOperationsByCompteId(id,pageable);
    }

    @GetMapping("/full/compte/{id}")
    public Compte getFullCompte(@PathVariable("id") Long id){
        Compte compte = compteOpsService.getCompteDetail(id);
        compte.setClient(consumer.findClientById(compte.getClientID()));
        return compte;
    }
}

@Data
class Virement{
    private Long compteCode;
    private double montant;
}

@Data
class VirementToCompte{
    private Long compteCodeSource;
    private Long compteCodeDestination;
    private double montant;
}
