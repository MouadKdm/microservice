package api.erp.LoginJWT.exceptions;

public class MontantInsuffisantResponse {

     private String monatant;

    public MontantInsuffisantResponse(String monatant) {
        this.monatant = monatant;
    }

    public String getMonatant() {
        return monatant;
    }

    public void setMonatant(String monatant) {
        this.monatant = monatant;
    }
}
