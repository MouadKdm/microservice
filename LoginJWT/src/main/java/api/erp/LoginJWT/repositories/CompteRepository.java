package api.erp.LoginJWT.repositories;

import api.erp.LoginJWT.domain.Abonne;
import api.erp.LoginJWT.domain.Compte;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteRepository extends CrudRepository<Compte,Long>
{
	Compte findByCompteId(Long compteId);
	Compte findByCompteNumber(String compteNumber);
	List<Compte> findByAbonne(Abonne abonne);
}
