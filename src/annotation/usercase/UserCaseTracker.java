package annotation.usercase;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by EricLi on 2017/1/9.
 * Email me : EricLi1235@gmail.com.
 */
public class UserCaseTracker {
    public static void trackUseCases(List<Integer> useCases, Class<?> cl){
        for(Method m : cl.getDeclaredMethods()){
            UserCase uc = m.getAnnotation(UserCase.class);
            if(uc != null){
                System.out.println("Found Use Case:" + uc.id() + " " + uc.description());
                useCases.remove(new Integer(uc.id()));
            }
        }
        for(int i : useCases){
            System.out.println("Warning: Missing use case-" + i);
        }

    }

    public static void main(String[] args){
        List<Integer> useCases = new ArrayList<>();
        Collections.addAll(useCases, 47,48,49,50);
        trackUseCases(useCases,PasswordUtils.class);
    }
}
