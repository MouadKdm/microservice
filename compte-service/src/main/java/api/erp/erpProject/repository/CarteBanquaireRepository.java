package api.erp.erpProject.repository;


import api.erp.erpProject.model.CarteBanquaire;

import api.erp.erpProject.model.Compte;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource
public interface CarteBanquaireRepository extends CrudRepository<CarteBanquaire,Long>
{
	 List<CarteBanquaire> findByCompte(Compte compte);
	 CarteBanquaire getById(Long id);
//	@Modifying
//	@Query("update CreditCard cc set cc.active=true where cc.creditCardId=?1")
//	@Transactional
//	public void ActivateCard(int creditCard);
//	@Modifying
//	@Query("update CreditCard cc set cc.active=false where cc.creditCardId=?1")
//	@Transactional
//	public void DisActivateCard(int creditCard);
}
