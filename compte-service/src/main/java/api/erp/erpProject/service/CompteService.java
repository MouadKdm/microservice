package api.erp.erpProject.service;


import api.erp.erpProject.model.Abonne;
import api.erp.erpProject.model.CarteBanquaire;
import api.erp.erpProject.model.Compte;
import api.erp.erpProject.model.Transaction;
import api.erp.erpProject.repository.AbonneRepository;
import api.erp.erpProject.repository.CarteBanquaireRepository;
import api.erp.erpProject.repository.CompteRepository;
import api.erp.erpProject.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Service
public class CompteService
{
	@Autowired
	AbonneRepository abonneRepository;
	
	@Autowired
	CompteRepository compteRepository;
	
	@Autowired
	CarteBanquaireRepository carteBanquaireRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	
	public Compte findAccountById(Long id)
	{
		return compteRepository.findByCompteId(id);
	}


	public CarteBanquaire createCarteBanquaire(CarteBanquaire carteBanquaire){

		CarteBanquaire carteBanquaire1 = carteBanquaireRepository.save(carteBanquaire);
		return carteBanquaire1 ;
	}
	
	public List<Compte> findAllAccounts(String username)
	{
        Abonne abonne=abonneRepository.findByUsername(username);
        List<Compte> comptes = compteRepository.findByAbonne(abonne);
        return comptes;
    }
	
	
	public List<CarteBanquaire> findCardsByAccount(Long id)
	{
		Compte compte=findAccountById(id);
		List<CarteBanquaire> carteBanquaires = carteBanquaireRepository.findByCompte(compte);
		return carteBanquaires;
	}
	
	public List<Transaction> findTransactionByCard(Long id)
	{
		CarteBanquaire carteBanquaire=carteBanquaireRepository.getById(id);
		List<Transaction> transactions = transactionRepository.findByCarteBanquaire(carteBanquaire);
		return transactions;
	}
	
	public List<CarteBanquaire> findAllcards(String username)
	{
		List<CarteBanquaire> carteBanquaires=new ArrayList<CarteBanquaire>();
		List<Compte> comptes=findAllAccounts(username);
		comptes.forEach((compte)-> carteBanquaireRepository.findByCompte(compte)
				.forEach(carteBanquaire -> carteBanquaires.add(carteBanquaire)));
		return carteBanquaires;
	}
	
	public List<Transaction> findAllTransactions(String username)
	{
		List<Transaction> transactions=new ArrayList<Transaction>();
		List<CarteBanquaire> carteBanquaires=findAllcards(username);
		carteBanquaires.forEach((carteBanquaire)->transactionRepository.findByCarteBanquaire(carteBanquaire)
				.forEach(transaction -> transactions.add(transaction)));
		return transactions;
	}
	
//	public void UpdateCreditActive(int creditCardId)
//	{
//		CarteBanquaire card=creditCardRepository.findByCreditCardId(creditCardId);
//		if (card.isActive())
//		{
//			creditCardRepository.DisActivateCard(creditCardId);
//		}
//		else
//		{
//			creditCardRepository.ActivateCard(creditCardId);
//		}
//	}
	
	
}
