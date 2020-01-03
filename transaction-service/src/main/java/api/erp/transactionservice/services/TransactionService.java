package api.erp.transactionservice.services;


import api.erp.transactionservice.model.Compte;
import api.erp.transactionservice.model.Transaction;
import api.erp.transactionservice.repositories.CompteRepository;
import api.erp.transactionservice.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository ;
    @Autowired
    private CompteRepository compteRepository ;


    public List<Transaction> findAllTransaction(){
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions ;
    }

    public Transaction findTransactionById(Long id ){
        Transaction transaction = transactionRepository.getById(id);
        return  transaction ;
    }
    public Transaction createTransaction(Transaction transaction){
//        CarteBanquaire carteBanquaire = carteBanquaireRepository.findByNumeroCompte(numeroCompte);
//        Compte compte = compteRepository.findByCompteNumber(numeroCompte);
//        transaction.setNumeroCompte(compte.get);
//        transaction.setCarteBanquaire(carteBanquaire);
        Transaction transaction1 = transactionRepository.save(transaction);
        return transaction1 ;
    }

    public List<Transaction> findTransactionsByNumeroCompte(String numeroCompte){
        List<Transaction> transactions = transactionRepository.findByNumeroCompte(numeroCompte);
        return transactions ;
    }
}
