package api.erp.contractservice.controller;


import api.erp.contractservice.model.Abonne;
import api.erp.contractservice.model.Agent;
import api.erp.contractservice.model.Contract;
import api.erp.contractservice.repositories.AbonneRepository;
import api.erp.contractservice.repositories.AgentRepository;
import api.erp.contractservice.repositories.ContractRepository;
import api.erp.contractservice.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/contract")
@CrossOrigin

public class ContractController {
    @Autowired
    private ContractService contractService ;
    @Autowired
    private ContractRepository contractRepository ;
    @Autowired
    private AbonneRepository abonneRepository;
    @Autowired
    private AgentRepository agentRepository ;
    @PostMapping("add/{username}")
    public ResponseEntity<?> createContract(@RequestBody Contract contract,@PathVariable String username){

        RestTemplate restTemplate = new RestTemplate();

        Abonne abonne = restTemplate.getForObject("http://localhost:8081/user/idAbonne/"+username, Abonne.class);
        abonneRepository.save(abonne);

        Contract contract1 = contractService.saveOrUpdate(contract,abonne);
        return new ResponseEntity<Contract>(contract1, HttpStatus.CREATED);
    }
    @GetMapping("allContracts")
    public ResponseEntity<?> getAllContracts(){
        List<Contract> contracts = contractService.findAllConracts();
        return new ResponseEntity<List<Contract>>(contracts, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getContractById(@PathVariable Long id){
       Contract contract = contractService.findConractById(id);
        return new ResponseEntity<Contract>(contract, HttpStatus.OK);
    }
    @GetMapping("/abonne/{username}")
    public ResponseEntity<?> getAllContracts(@PathVariable String username){
        Contract contract = contractService.findContractByAbonne(username);
        return new ResponseEntity<Contract>(contract, HttpStatus.OK);
    }

}
