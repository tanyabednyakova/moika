import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by blajimir on 14.02.2017.
 */
public class TestClassBCrypt {
    @Test
    public void test(){
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String rawpass = "db";
        String pass = new BCryptPasswordEncoder().encode(rawpass);
        System.out.printf("rawpass: %s pass: %s%n",rawpass,pass);
        System.out.println("crypt matches: " + bcrypt.matches(rawpass,pass));
    }
}
