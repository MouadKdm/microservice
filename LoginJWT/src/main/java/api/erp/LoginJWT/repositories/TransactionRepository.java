package api.erp.LoginJWT.repositories;

import api.erp.LoginJWT.domain.Transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Integer>
{
}
