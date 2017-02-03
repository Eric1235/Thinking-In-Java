package reflecttest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by EricLi on 2016/12/1.
 * Email me : EricLi1235@gmail.com.
 */
public class Reflecttest {
    private static String useage =
            "usage :\n"
            +"ShowMethods qualified.class.name\n"
            +"To show all methods in class or:\n"
            +"ShowMethods qualified.class.name word\n"
            +"To search for methods involving 'word'";
    private static String s = "(final)|(native)";
    private static Pattern pattern = Pattern.compile("(\\w+\\.)|(final)|(native)");
    public static void main(String[] args){
        if(args.length < 1){
            System.out.println(useage);
            System.exit(0);
        }
        int lines = 0;
        try {
            Class<?> c = Class.forName(args[0]);
            Method[] methods = c.getMethods();
            Constructor[] constructors = c.getConstructors();
            if(args.length == 1){
                for(Method method:methods){
                    Matcher matcher = pattern.matcher(method.toString());
                    System.out.println(pattern.matcher(method.toString()).replaceAll(" "));
                }
                for(Constructor constructor:constructors){
                    System.out.println(pattern.matcher(constructor.toString()).replaceAll(" "));
                }
                lines = methods.length + constructors.length;
            }else{
                for(Method method:methods){
                    if(method.toGenericString().indexOf(args[1]) != -1){
                        System.out.println(pattern.matcher(method.toString()).replaceAll(" "));
                        lines++;
                    }
                }
                for(Constructor constructor:constructors){
                    if(constructor.toString().indexOf(args[1])!=-1){
                        System.out.println(pattern.matcher(constructor.toString()).replaceAll(" "));
                        lines++;
                    }
                }
            }



        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
