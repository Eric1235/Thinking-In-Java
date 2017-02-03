package proxy;

import java.lang.reflect.Method;

/**
 * Created by EricLi on 2016/12/6.
 * Email me : EricLi1235@gmail.com.
 */
public class HiddenImplementation {
    public static void main(String[] args)throws Exception{
        A a = HiddenC.makeA();
        a.a();
        System.out.println(a.getClass().getCanonicalName());

        callHiddenMethod(a, "a");
        callHiddenMethod(a, "u");
        callHiddenMethod(a, "v");
        callHiddenMethod(a, "w");
    }
    static void callHiddenMethod(Object a, String methodName) throws Exception{
        Method g = a.getClass().getDeclaredMethod(methodName);
        g.setAccessible(true);//使用反射，方法依旧是可达的
        g.invoke(a);
    }
}
