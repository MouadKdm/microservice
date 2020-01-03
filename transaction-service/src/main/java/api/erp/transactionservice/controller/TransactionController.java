package api.erp.transactionservice.controller;

import api.erp.transactionservice.model.Beneficiare;
import api.erp.transactionservice.model.Transaction;
import api.erp.transactionservice.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("transaction")
@CrossOrigin

public class TransactionController {

    @Autowired
    private TransactionService transactionService ;



    @GetMapping("/all")
    public ResponseEntity<?> getAllTransactions(){
//        RestTemplate restTemplate = new RestTemplate();
//restTemplate.getForObject("",);

        List<Transaction> transactions = transactionService.findAllTransaction();
        return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);

    }
    @GetMapping("id/{id}")
    public ResponseEntity<?> getTransactionsById(@PathVariable Long id){

        Transaction transaction = transactionService.findTransactionById(id);
        return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);

    }
    @PostMapping("/add")
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction){

        Transaction transaction1 = transactionService.createTransaction(transaction);
        return new ResponseEntity<Transaction>(transaction1, HttpStatus.CREATED);

    }
    @GetMapping("/numeroCompte/{numeroCompte}")
    public ResponseEntity<?> getAllTransactionsByNumeroCompte(@PathVariable String numeroCompte){

        List<Transaction> transactions = transactionService.findTransactionsByNumeroCompte(numeroCompte);
        return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);

    }
}
