package Utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    public static boolean isValid(String passwordCheck, String password){
        return BCrypt.checkpw(passwordCheck, password);
    }

    public static String generatePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}
