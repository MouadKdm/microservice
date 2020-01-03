package api.erp.transactionservice.repositories;

import api.erp.transactionservice.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {

    List<Transaction> findAll();
    Transaction getById(Long id );
    List<Transaction> findByNumeroCompte(String numeroCompte) ;
}
