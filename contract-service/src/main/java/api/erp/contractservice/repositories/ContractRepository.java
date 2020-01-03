package api.erp.contractservice.repositories;

import api.erp.contractservice.model.Abonne;
import api.erp.contractservice.model.Contract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContractRepository extends CrudRepository<Contract,Long> {

    List<Contract> findAll();

    Contract getById(Long id);
    Contract findByAbonne(Abonne abonne);
}
