package mg.rjc.testcars.utils;

public class SecurityUtil {
    public static final String JWT_SECRET = "secret";
    public static final long JWT_EXPIRE_ACCESS_TOKEN = 10 * 60 * 1000;
    public static final String JWT_PREFIX = "Bearer ";
    public static final String JWT_ACCESS_TOKEN_KEY = "access_token";
    public static final String JWT_ROLES_KEY = "roles";
    public static final String USERNAME_PARAM = "username";
    public static final String PASSWORD_PARAM = "password";
    public static final String JWT_ERROR_MESSAGE_KEY = "error_message";
    public static final String JWT_ERROR_KEY = "error";
}
