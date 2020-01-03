package api.erp.erpProject.model;

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
public class CarteBanquaire implements Serializable
{	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
    String numeroCompte;
    String maximum;
    String dateExperation;
//    String creditScore;
    int type;
    boolean active;

    @ManyToOne
    Compte compte;

    @OneToMany(mappedBy = "carteBanquaire")
    List<Transaction> transactions;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
    

}
