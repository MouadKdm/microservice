package api.erp.LoginJWT.repositories;

import api.erp.LoginJWT.domain.Abonne;
import api.erp.LoginJWT.domain.Compte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbonneRepository extends CrudRepository<Abonne, Long> {
    Abonne findByUsername(String username);
    List<Abonne> findAll();
    Abonne getById(Long id);
    Abonne findByComptes(Compte compte);


}