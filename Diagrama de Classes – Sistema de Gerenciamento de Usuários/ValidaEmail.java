import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaEmail {

    public static void main(String[] args) {
        String email = "Twiggier219@gmail.com"; // Email que será validado
        System.out.println("Email: " + email);
        System.out.println("Email é válido: " + isValido(email));
    }

    public static boolean isValido(String email) {
        String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}