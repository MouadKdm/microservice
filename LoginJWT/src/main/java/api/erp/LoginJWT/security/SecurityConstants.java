package api.erp.LoginJWT.security;

public class SecurityConstants {

    public static final String SIGN_UP_URLS = "/user/**";
    public static final String SIGN_UP_URLS1 = "/agent/**";
//    public static final String SIGN_UP_URLS3 = "/compte/**";


    public static final String H2_URL = "h2-console/**";
    public static final String SECRET ="SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX= "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 3000000_00000l; //300 seconds
}
