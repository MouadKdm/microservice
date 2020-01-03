package api.erp.erpProject.controller;



import api.erp.erpProject.model.Abonne;
import api.erp.erpProject.model.CarteBanquaire;
import api.erp.erpProject.model.Compte;
import api.erp.erpProject.model.Transaction;
import api.erp.erpProject.repository.CompteRepository;
import api.erp.erpProject.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/compte")
@CrossOrigin
public class CompteController
{
	
		@Autowired
		CompteService compteService;
		@Autowired
		CompteRepository compteRepository;

		@PostMapping("/addCompte/{id}")
		public ResponseEntity<?> createCompte(@RequestBody Compte compte,@PathVariable Long id){

			RestTemplate restTemplate = new RestTemplate();
			Abonne abonne = restTemplate.getForObject("http://localhost:8081/user/idAbonne/"+id, Abonne.class);
			compte.setAbonne(abonne);
		Compte compte1 = compteRepository.save(compte);
		return new ResponseEntity<Compte>(compte1,HttpStatus.CREATED);
		}

		@GetMapping("compteNumber/{compteNumber}")
		public Compte findAccountByCompteNumber(String compteNumber)
		{
		return compteRepository.findByCompteNumber(compteNumber);
		}


		@PostMapping("/addCarteBanquaire/{compteNumber}")
		public ResponseEntity<?> createCards(@RequestBody CarteBanquaire carteBanquaire,@PathVariable String compteNumber){
//			RestTemplate restTemplate = new RestTemplate();
//			Compte compte = restTemplate.getForObject("http://localhost:8083/agent/getCompte/"+compteNumber,Compte.class);
			Compte compte = compteRepository.findByCompteNumber(compteNumber);
			carteBanquaire.setCompte(compte);
			CarteBanquaire carteBanquaire1 = compteService.createCarteBanquaire(carteBanquaire);
			return new ResponseEntity<CarteBanquaire>(carteBanquaire1,HttpStatus.CREATED);
		}
		
		@GetMapping("compteId/{compteId}")
		public ResponseEntity<?> getAccountById(@PathVariable Long compteId)
		{
			Compte compte=compteService.findAccountById(compteId);
			return new ResponseEntity<Compte>(compte,HttpStatus.OK);
		}
		
		@GetMapping("carteByCompte/{id}")
		public ResponseEntity<?> getCardsByAccount(@PathVariable Long id)
		{
			List<CarteBanquaire> carteBanquaires=compteService.findCardsByAccount(id);
			return new ResponseEntity<Iterable<CarteBanquaire>>(carteBanquaires,HttpStatus.OK);
		}
		
		@GetMapping("transactionByCarte/{carteId}")
		public ResponseEntity<?> getTransactionsByCard(@PathVariable Long carteId)
		{
			List<Transaction> transactions=compteService.findTransactionByCard(carteId);
			return new ResponseEntity<List<Transaction>>(transactions,HttpStatus.OK);
		}
		
		
	    @GetMapping("/all/comptes/{username}")
	    ResponseEntity<?> getAllAccounts(@PathVariable String username/*Principal principal*/)
	    {
	        List<Compte> comptes = compteService.findAllAccounts(username/*principal.getName()*/);
	        return new ResponseEntity<List<Compte>>(comptes,HttpStatus.OK);
	    }
	    
	    
	    @GetMapping("all/cartes/{username}")
	    ResponseEntity<?> getAllCards(@PathVariable String username/*Principal principal*/)
		{
		   List<CarteBanquaire> carteBanquaires = compteService.findAllcards(username/*principal.getName()*/);
		   return new ResponseEntity<List<CarteBanquaire>>(carteBanquaires,HttpStatus.OK);
		}
	    
	    
	    
	    @GetMapping("all/transactions/{username}")
	    ResponseEntity<?> getAllTransactions(@PathVariable String username/*Principal principal*/)
	    {
	        List<Transaction> transactions = compteService.findAllTransactions(username/*principal.getName()*/);
	        return new ResponseEntity<List<Transaction>>(transactions,HttpStatus.OK);
	    }
	    
	    
//
//	    @PutMapping("/modify")
//	    void updateCard(@RequestParam(name="creditCardId") int creditCardId)
//	    {
//	    	compteService.UpdateCreditActive(creditCardId);
//	    }
//
	   

}
