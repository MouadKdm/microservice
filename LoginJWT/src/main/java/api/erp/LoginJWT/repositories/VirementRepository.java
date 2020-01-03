package api.erp.LoginJWT.repositories;

import api.erp.LoginJWT.domain.Virement;
import org.springframework.data.repository.CrudRepository;

public interface VirementRepository extends CrudRepository<Virement,Long> {
}
