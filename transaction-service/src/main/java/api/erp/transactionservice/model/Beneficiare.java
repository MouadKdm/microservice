package api.erp.transactionservice.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity

public class Beneficiare {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id ;
    String nom ;
    String numeroCompte ;
    @ManyToOne(fetch = FetchType.LAZY)
    Compte compte ;
}
