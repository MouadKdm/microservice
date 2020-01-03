package api.erp.LoginJWT.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NumeroCompteNotFoundException  extends RuntimeException {

    public NumeroCompteNotFoundException(String message) {
        super(message);
    }
}
