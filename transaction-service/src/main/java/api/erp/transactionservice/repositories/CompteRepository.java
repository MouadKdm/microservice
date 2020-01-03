package api.erp.transactionservice.repositories;

import api.erp.transactionservice.model.Compte;
import org.springframework.data.repository.CrudRepository;

public interface CompteRepository  extends CrudRepository<Compte,Long> {
    Compte findByCompteNumber(String compteNumber);
}
