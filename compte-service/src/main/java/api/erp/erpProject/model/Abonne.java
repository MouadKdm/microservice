package api.erp.erpProject.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity

public class Abonne implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
	String username;
    String fullName;
    Calendar birthday;
    @Embedded
    Adress adress ;
//    String address;
//    String city;
//    String state;
//    String zipCode;
//    String country;
    String email;
    String sex;
    

    @OneToMany(mappedBy = "abonne")
    List<Compte> comptes;

    @OneToOne(cascade = CascadeType.ALL)
    Contract contract;
}
