package api.erp.LoginJWT.controller;



import api.erp.LoginJWT.domain.Abonne;
import api.erp.LoginJWT.domain.Compte;
import api.erp.LoginJWT.domain.SmsRequest;
import api.erp.LoginJWT.domain.Transaction;
import api.erp.LoginJWT.exceptions.MontantInsuffisantException;
import api.erp.LoginJWT.repositories.AbonneRepository;
import api.erp.LoginJWT.repositories.CompteRepository;
import api.erp.LoginJWT.repositories.TransactionRepository;
import api.erp.LoginJWT.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/compte")
@CrossOrigin

public class CompteController {

	@Autowired
	CompteService compteService;
	@Autowired
	CompteRepository compteRepository;
	@Autowired
	AbonneRepository abonneRepository;
	@Autowired
	TransactionRepository transactionRepository;


	@GetMapping("compteNumber/{compteNumber}")
	public Compte findAccountByCompteNumber(String compteNumber) {
		return compteRepository.findByCompteNumber(compteNumber);
	}


	@GetMapping("compteId/{compteId}")
	public ResponseEntity<?> getAccountById(@PathVariable Long compteId) {
		Compte compte = compteService.findAccountById(compteId);
		return new ResponseEntity<Compte>(compte, HttpStatus.OK);
	}


	@GetMapping("/all/comptes")
	ResponseEntity<?> getAllAccounts(Principal principal) {
		List<Compte> comptes = compteService.findAllAccounts(principal.getName());
		return new ResponseEntity<List<Compte>>(comptes, HttpStatus.OK);
	}

	@GetMapping("/soldeComptes")
	public ResponseEntity<?> getAllSoldesCompte(Principal principal) {
		List<Double> soldes = compteService.getSoldes(principal.getName());
		return new ResponseEntity<List<Double>>(soldes, HttpStatus.OK);
	}

	@GetMapping("/soldeTotal")
	public ResponseEntity<?> soldeAbonne(Principal principal) {
		double solde = compteService.calculerSolde(principal.getName());

		return new ResponseEntity<Double>(solde, HttpStatus.OK);
	}

	@PostMapping("/virement/{numeroCompte1}/{numeroCompte2}/{montant}")
	public ResponseEntity<?> virementSolde(@PathVariable String numeroCompte1,
										   @PathVariable String numeroCompte2,
										   @PathVariable double montant) {
		Compte compte = compteRepository.findByCompteNumber(numeroCompte1);
		Abonne abonne = abonneRepository.findByComptes(compte);
		compteService.Virement(numeroCompte1, numeroCompte2, montant);
		RestTemplate restTemplate = new RestTemplate();
		Transaction transaction = new Transaction();
		transaction.setNumeroCompte(numeroCompte1);
		transaction.setSecondAccountId(numeroCompte2);
		transaction.setType("virement");
		transaction.setMontant(montant);
		transaction.setAbonne(abonne);
		String theUrl = "http://localhost:8085/transaction/add";
		HttpEntity<Transaction> requestEntity = new HttpEntity<>(transaction);

		ResponseEntity<Transaction> response = restTemplate.exchange(theUrl, HttpMethod.POST, requestEntity, Transaction.class);
		transactionRepository.save(transaction);

		return new ResponseEntity<String>("with success", HttpStatus.OK);
	}


	@GetMapping("/allTransactions")
	public ResponseEntity<?> getAllTransactions(Principal principal) {

		List<Transaction> transactions = compteService.getTransactions(principal.getName());


		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}

	@PostMapping("/recharge/{compteNumber}/{montant}")
	public ResponseEntity<?> rechargeCompte(@RequestBody SmsRequest smsRequest, @PathVariable String compteNumber, @PathVariable int montant) {
		Compte compte = compteRepository.findByCompteNumber(compteNumber);

		double soldeActuel = compte.getSoldeCompte() - montant;
		if (soldeActuel > 0) {


			compte.setSoldeCompte(soldeActuel);
			compteRepository.save(compte);
			smsRequest.setMessage("vous avez recu une recharge de " + montant + " Dh avec succes");


			RestTemplate restTemplate = new RestTemplate();
			String theUrl = "http://localhost:8080/api/v1/sms";
			HttpEntity<SmsRequest> requestEntity = new HttpEntity<>(smsRequest);
			ResponseEntity<SmsRequest> response = restTemplate.exchange(theUrl, HttpMethod.POST, requestEntity, SmsRequest.class);

			Abonne abonne = abonneRepository.findByComptes(compte);
			String theUrl1 = "http://localhost:8085/transaction/add";
			Transaction transactionRecharge = new Transaction();

			HttpEntity<Transaction> requestEntity1 = new HttpEntity<>(transactionRecharge);

			transactionRecharge.setNumeroCompte(compteNumber);
			transactionRecharge.setSecondAccountId(smsRequest.getPhoneNumber());
			transactionRecharge.setType("Recharge");
			transactionRecharge.setMontant(montant);
			transactionRecharge.setAbonne(abonne);


			ResponseEntity<Transaction> response1 = restTemplate.exchange(theUrl1, HttpMethod.POST, requestEntity1, Transaction.class);
			transactionRepository.save(transactionRecharge);
			return new ResponseEntity<String>("with success", HttpStatus.OK);
		}
			 else
				throw new MontantInsuffisantException("votre solde est insuffisant");






//return null ;

	}
}
