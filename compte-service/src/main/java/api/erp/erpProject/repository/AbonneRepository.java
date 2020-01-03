package api.erp.erpProject.repository;


import api.erp.erpProject.model.Abonne;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


@RepositoryRestResource
public interface AbonneRepository extends CrudRepository<Abonne,Long>
{
		Abonne findByUsername(String username);
}
