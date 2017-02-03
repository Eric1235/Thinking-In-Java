package util;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by EricLi on 2016/12/5.
 * Email me : EricLi1235@gmail.com.
 */
public class PPrint {
    public static void pprint(String s){
        System.out.println(s);
    }

    public static String pformat(Collection<?> c){
        if(c.size() == 0){
            return "[]";
        }
        StringBuilder result = new StringBuilder("[");
        for(Object elem : c){
            if(c.size() != 1){
                result.append("\n ");
            }
            result.append(elem);
        }
        if(c.size() != 1){
            result.append("\n");
        }
        result.append("]");
        return result.toString();
    }

    public static void ppring(Collection<?> c){
        System.out.println(pformat(c));
    }

    public static void pprint(Object[] c){
        System.out.println(pformat(Arrays.asList(c)));
    }
}
