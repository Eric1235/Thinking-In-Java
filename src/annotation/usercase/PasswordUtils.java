package annotation.usercase;

import java.util.List;

/**
 * Created by EricLi on 2017/1/9.
 * Email me : EricLi1235@gmail.com.
 */
public class PasswordUtils {

    @UserCase(id = 47,description = "Passwords must contain at least one numberic")
    public boolean validatePassword(String password){
        return password.matches("\\w*\\d\\w*");
    }

    @UserCase(id = 48)
    public String encryptPasseord(String password){
        return new StringBuffer(password).reverse().toString();
    }

    @UserCase(id = 49, description = "New Passwords can't equal previously used ones")
    public boolean booleancheckForNewPassword(List<String>prePasswords, String password){
        return !prePasswords.contains(password);
    }

}
