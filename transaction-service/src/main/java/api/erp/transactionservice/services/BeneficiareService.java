package api.erp.transactionservice.services;

import api.erp.transactionservice.model.Beneficiare;
import api.erp.transactionservice.repositories.BeneficiareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiareService {
    @Autowired
    private BeneficiareRepository beneficiareRepository ;

    public List<Beneficiare> findAllBeneficiare(){

        List<Beneficiare> beneficiares = beneficiareRepository.findAll();
        return beneficiares ;

    }
    public Beneficiare findBeneficiareById(Long id){

        Beneficiare beneficiare = beneficiareRepository.getById(id);
        return beneficiare ;

    }
    public Beneficiare createBeneficiare(Beneficiare beneficiare){
        Beneficiare beneficiare1 = beneficiareRepository.save(beneficiare);
        return beneficiare1 ;
    }
    public void deleteBeneficiare(Long id ){
        beneficiareRepository.deleteById(id);
    }
}

