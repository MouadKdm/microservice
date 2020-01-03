package api.erp.LoginJWT.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Virement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCompte1;
    private String numeroCompte2;
    private double montant;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateCreation = new Date();
    @JsonIgnore
    @ManyToOne
    Compte compte;

    public Virement(String numeroCompte1, String numeroCompte2, double montant, Date dateCreation, Compte compte) {
        this.numeroCompte1 = numeroCompte1;
        this.numeroCompte2 = numeroCompte2;
        this.montant = montant;
        this.dateCreation = dateCreation;
        this.compte = compte;
    }
}
