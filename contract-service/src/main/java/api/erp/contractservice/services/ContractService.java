package api.erp.contractservice.services;


import api.erp.contractservice.model.Abonne;
import api.erp.contractservice.model.Contract;
import api.erp.contractservice.repositories.AbonneRepository;
import api.erp.contractservice.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository ;
    @Autowired
    private AbonneRepository abonneRepository ;

    public Contract saveOrUpdate(Contract contract , Abonne abonne){
//        Abonne abonne = abonneRepository.getById(id);
        contract.setAbonne(abonne);

        Contract contract1 = contractRepository.save(contract);

        return contract1 ;
    }

    public List<Contract> findAllConracts(){
        List<Contract> contracts = contractRepository.findAll();
        return contracts ;
    }

    public Contract findConractById(Long id){
        Contract contract = contractRepository.getById(id);
        return contract;
    }
    public Contract findContractByAbonne(String username){
        Abonne abonne = abonneRepository.findByUsername(username);
        Contract contract = contractRepository.findByAbonne(abonne);
        return contract;
    }
}
