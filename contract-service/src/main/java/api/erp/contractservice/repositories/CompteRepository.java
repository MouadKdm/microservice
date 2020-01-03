package api.erp.contractservice.repositories;


import api.erp.contractservice.model.Abonne;
import api.erp.contractservice.model.Compte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteRepository extends CrudRepository<Compte, Long>
{
	Compte findByCompteId(Long compteId);
	List<Compte> findByAbonne(Abonne abonne);
}
