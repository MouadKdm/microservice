package api.erp.erpProject;

import api.erp.erpProject.model.Compte;
import api.erp.erpProject.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ErpProjectApplication implements CommandLineRunner {
	@Autowired
	private CompteRepository compteRepository ;

	public static void main(String[] args) {
		SpringApplication.run(ErpProjectApplication.class, args);


	}

	@Override
	public void run(String... args) throws Exception {

		Compte compte = new Compte();
		compteRepository.save(compte);
	}
}
