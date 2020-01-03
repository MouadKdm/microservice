package api.erp.transactionservice.repositories;

import api.erp.transactionservice.model.Beneficiare;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BeneficiareRepository extends CrudRepository<Beneficiare,Long> {
    Beneficiare getById(Long id );
    List<Beneficiare> findAll();
    void deleteById(Long id);
}
