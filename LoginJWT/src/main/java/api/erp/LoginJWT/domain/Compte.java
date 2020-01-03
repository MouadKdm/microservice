package api.erp.LoginJWT.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
    @Column(unique = true)
    String compteNumber;
    String compteLeader;
    String password;
    Boolean active;
    String typeCompte;
    double soldeCompte ;
    String dateCreation;
    Boolean valid;
    String verificationCode;

    @OneToMany(cascade =  CascadeType.REFRESH , fetch = FetchType.EAGER ,mappedBy = "compte" )
    private List<Virement> virements = new ArrayList<>();
    @ManyToOne
    Abonne abonne;




}
