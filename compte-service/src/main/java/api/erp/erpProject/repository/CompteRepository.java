package api.erp.erpProject.repository;

import api.erp.erpProject.model.Abonne;
import api.erp.erpProject.model.Compte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
public interface CompteRepository extends CrudRepository<Compte,Long>
{
	Compte findByCompteId(Long compteId);
	Compte findByCompteNumber(String compteNumber);
	List<Compte> findByAbonne(Abonne abonne);
}
