package api.erp.transactionservice.controller;


import api.erp.transactionservice.model.Beneficiare;
import api.erp.transactionservice.services.BeneficiareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/benefeciaire")
public class beneficiaireController {
    @Autowired
    private BeneficiareService beneficiareService ;
    public ResponseEntity<?> getAllBeneficiare(){

        List<Beneficiare> beneficiares = beneficiareService.findAllBeneficiare();
        return new ResponseEntity<List<Beneficiare>>(beneficiares, HttpStatus.OK);

    }
    @GetMapping("id/{id}")
    public ResponseEntity<?> getBeneficiareById(@PathVariable Long id){

        Beneficiare beneficiare = beneficiareService.findBeneficiareById(id);
        return new ResponseEntity<Beneficiare>(beneficiare, HttpStatus.OK);

    }
    public ResponseEntity<?> createBeneficiaire(@RequestBody Beneficiare beneficiare){

        Beneficiare beneficiare1 = beneficiareService.createBeneficiare(beneficiare);
        return new ResponseEntity<Beneficiare>(beneficiare1, HttpStatus.OK);

    }
}
