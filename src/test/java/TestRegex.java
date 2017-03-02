import org.junit.Test;

public class TestRegex {
    @Test
    public void test(){
        String regex = "^[A-Za-z]+|^[А-Яа-я]+";
        System.out.println("Alex".matches(regex));
        System.out.println("Алекс".matches(regex));
        System.out.println("Alex123".matches(regex));
        System.out.println("Алексandr".matches(regex));
    }
}
