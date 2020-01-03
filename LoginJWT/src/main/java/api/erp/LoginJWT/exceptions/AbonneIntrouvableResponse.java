package api.erp.LoginJWT.exceptions;

public class AbonneIntrouvableResponse {
    private String abonne ;

    public AbonneIntrouvableResponse(String abonne) {
        this.abonne = abonne;
    }

    public String getAbonne() {
        return abonne;
    }

    public void setAbonne(String abonne) {
        this.abonne = abonne;
    }
}
