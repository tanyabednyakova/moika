import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestRegex {
    @Test
    public void test(){
        String regex = "^[A-Za-z]+|^[А-Яа-я]+";
        System.out.println("Alex".matches(regex));
        System.out.println("Алекс".matches(regex));
        System.out.println("Alex123".matches(regex));
        System.out.println("Алексandr".matches(regex));
        Map<String,Object> map = new HashMap<>();
        map.put("odin","dvar");
        System.out.println("key: odin"+"  val: "+map.get("odin").toString());
    }
}
