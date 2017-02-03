package genericity.genericvarargs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EricLi on 2016/12/10.
 * Email me : EricLi1235@gmail.com.
 */
public class GenericVarargs {
    public static <T> List<T> makeList(T...args){
        List<T> result = new ArrayList<T>();
        for(T a : args){
            result.add(a);
        }
        return result;
    }

    public static void main(String[] args){
        GenericVarargs gn = new GenericVarargs();
        List<Integer> ls = gn.makeList(1,2,3);
        System.out.println(ls);
        List<String> ls1 = gn.makeList("1", "2", "3");
        System.out.println(ls1);
    }
}
