package api.erp.erpProject.repository;

import api.erp.erpProject.model.CarteBanquaire;
import api.erp.erpProject.model.Transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
public interface TransactionRepository extends CrudRepository<Transaction,Integer>
{
	 List<Transaction> findByCarteBanquaire(CarteBanquaire carteBanquaire);
}
