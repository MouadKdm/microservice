package api.erp.transactionservice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
public class Compte implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long compteId;
    String compteNumber;
    String password;
    Boolean active;
    String typeCompte;
//    int minBalanceRestriction;
//    int accountBalance;
    String dateCreation;
    Boolean valid;
    String verificationCode;

    @OneToMany(mappedBy = "compte")
    List<Beneficiare> beneficiares ;

    @ManyToOne
    Abonne abonne;



}
