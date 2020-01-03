package api.erp.LoginJWT.controller;

import api.erp.LoginJWT.domain.Abonne;
import api.erp.LoginJWT.domain.Compte;
import api.erp.LoginJWT.exceptions.NumeroCompteNotFoundException;
import api.erp.LoginJWT.payload.JWTLoginSucessReponse;
import api.erp.LoginJWT.payload.LoginRequest;
import api.erp.LoginJWT.repositories.AbonneRepository;
import api.erp.LoginJWT.repositories.CompteRepository;
import api.erp.LoginJWT.security.JwtTokenProvider;
import api.erp.LoginJWT.security.SecurityConstants;
import api.erp.LoginJWT.services.CompteService;
import api.erp.LoginJWT.services.MapValidationErrorService;
import api.erp.LoginJWT.services.AbonneService;
import api.erp.LoginJWT.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin

@RestController
@RequestMapping(value = "/user")
public class AbonneController {
    @Autowired
    private AbonneService userService ;
    @Autowired
    CompteService compteService;

    @Autowired
    private AbonneRepository userRepository ;
    @Autowired
    private MapValidationErrorService mapValidationErrorService ;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    CompteRepository compteRepository;
    @Autowired
    AbonneRepository abonneRepository ;




    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        Abonne user = userRepository.findByUsername(loginRequest.getUsername());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX +  tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSucessReponse(true, jwt));
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Abonne user , BindingResult result){
        userValidator.validate(user,result);
        ResponseEntity errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap ;
        Abonne user1 = userService.saveOrUpdateUser(user);
        return new ResponseEntity<Abonne>(user1, HttpStatus.CREATED);
    }

    @GetMapping("idAbonne/{username}")
    public ResponseEntity<?> getAbonneById(@PathVariable String username){
        Abonne abonne1 = userRepository.findByUsername(username);

        return new ResponseEntity<Abonne>(abonne1, HttpStatus.OK);

    }
    @PostMapping("/addCompte/{username}")
    public ResponseEntity<?> createCompte(@RequestBody Compte compte, @PathVariable String username){

//			RestTemplate restTemplate = new RestTemplate();
//			Abonne abonne = restTemplate.getForObject("http://localhost:8081/user/idAbonne/"+id, Abonne.class);
//			compte.setAbonne(abonne);

            Compte compte1 = userService.saveCompte(compte,username);
            return new ResponseEntity<Compte>(compte1,HttpStatus.CREATED);



    }
    @GetMapping("/getAllUsers")

    public ResponseEntity<?> getAllUsers()
    {
        List<Abonne> abonnes = abonneRepository.findAll();
        return new ResponseEntity<List<Abonne>>(abonnes,HttpStatus.OK);
    }
    //    @PostMapping("/addCarteBanquaire/{compteNumber}")
//    public ResponseEntity<?> createCards(@RequestBody CarteBanquaire carteBanquaire, @PathVariable String compteNumber){
////			RestTemplate restTemplate = new RestTemplate();
////			Compte compte = restTemplate.getForObject("http://localhost:8083/agent/getCompte/"+compteNumber,Compte.class);
//        Compte compte = compteRepository.findByCompteNumber(compteNumber);
//        carteBanquaire.setCompte(compte);
//        CarteBanquaire carteBanquaire1 = compteService.createCarteBanquaire(carteBanquaire);
//        return new ResponseEntity<CarteBanquaire>(carteBanquaire1,HttpStatus.CREATED);
//    }





}
