package api.erp.LoginJWT.services;


import api.erp.LoginJWT.domain.Abonne;
import api.erp.LoginJWT.domain.Compte;
import api.erp.LoginJWT.exceptions.AbonneIntrouvableException;
import api.erp.LoginJWT.exceptions.NumeroCompteNotFoundException;
import api.erp.LoginJWT.exceptions.UsernameAlreadyExistsException;
import api.erp.LoginJWT.repositories.AbonneRepository;
import api.erp.LoginJWT.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AbonneService {
    @Autowired
    private AbonneRepository userRepository ;
    @Autowired
    private CompteRepository compteRepository ;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder ;

    public Abonne saveOrUpdateUser(Abonne user){
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setUsername((user.getUsername()));
            user.setConfirmPassword("");
            return userRepository.save(user);
        }catch(Exception e){
            throw new UsernameAlreadyExistsException("username" + user.getUsername() + " already exist");
        }

    }

    public Compte saveCompte(Compte compte ,String username){


        Abonne abonne = userRepository.findByUsername(username);
            compte.setAbonne(abonne);

         try {

            compteRepository.save(compte);

            return compte;
        }catch (Exception ex){
            throw new NumeroCompteNotFoundException("le compte avec numero de compte "+ compte.getCompteNumber()+" deja existe");

        }



    }




}

