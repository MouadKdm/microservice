package api.erp.LoginJWT.services;


import api.erp.LoginJWT.domain.Abonne;
import api.erp.LoginJWT.domain.Compte;
import api.erp.LoginJWT.domain.Transaction;
import api.erp.LoginJWT.domain.Virement;
import api.erp.LoginJWT.exceptions.MontantInsuffisantException;
import api.erp.LoginJWT.exceptions.NumeroCompteNotFoundException;
import api.erp.LoginJWT.repositories.AbonneRepository;
import api.erp.LoginJWT.repositories.CompteRepository;
import api.erp.LoginJWT.repositories.TransactionRepository;
import api.erp.LoginJWT.repositories.VirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class CompteService
{
	@Autowired
	AbonneRepository abonneRepository;
	
	@Autowired
	CompteRepository compteRepository;
	@Autowired
	VirementRepository virementRepository ;
	

	
	@Autowired
	TransactionRepository transactionRepository;
	private double soldeTotal ;


	public List<Double> getSoldes(String username){
		Abonne abonne = abonneRepository.findByUsername(username);
		List<Compte> comptes = abonne.getComptes();
		List<Double> soldes = new ArrayList<>() ;
		comptes.forEach(compte -> soldes.add(compte.getSoldeCompte()));
		return soldes;
	}
	public List<Transaction> getTransactions(String username){
		Abonne abonne = abonneRepository.findByUsername(username);
		List<Transaction> transactions = abonne.getTransactions();

		return transactions;
	}

	public Compte findAccountById(Long id)
	{
		return compteRepository.findByCompteId(id);
	}



	public List<Compte> findAllAccounts(String username)
	{
        Abonne abonne=abonneRepository.findByUsername(username);
        List<Compte> comptes = compteRepository.findByAbonne(abonne);
        return comptes;
    }
	public Double calculerSolde(String username ){
		soldeTotal = 0;
		Abonne abonne = abonneRepository.findByUsername(username);
		List<Compte> comptes = abonne.getComptes();
		comptes.forEach(compte -> soldeTotal+=compte.getSoldeCompte());
		return soldeTotal ;
	}
	public void Virement(String numeroCompte1 , String  numeroCompte2 , Double montant){

		Compte compte1 = compteRepository.findByCompteNumber(numeroCompte1);
		Compte compte2;
		double solde1 = compte1.getSoldeCompte() - montant ;

		if (solde1>0){

			try {

				compte2 = compteRepository.findByCompteNumber(numeroCompte2);
				double solde2 = compte2.getSoldeCompte() + montant ;
				compte2.setSoldeCompte(solde2);
				compteRepository.save(compte2);

			}catch (Exception e ){
				throw new NumeroCompteNotFoundException("le compte avec numero de compte "+ numeroCompte2+"n'existe pas");
			}


			compte1.setSoldeCompte(solde1);



			compteRepository.save(compte1);
		}
		else
			throw new MontantInsuffisantException("votre solde est insuffisant");
		Date date = new Date() ;

		Virement virement = new Virement(numeroCompte1,numeroCompte2,montant,date,compte1);

		virementRepository.save(virement);


	}
	


	


	
	
}
