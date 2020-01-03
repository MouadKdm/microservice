package api.erp.LoginJWT.exceptions;

public class NumeroCompteNotFoundResponse {

    private  String numeroCompte ;

    public NumeroCompteNotFoundResponse(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }
}
