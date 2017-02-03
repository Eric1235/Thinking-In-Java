package proxy;

/**
 * Created by EricLi on 2016/12/6.
 * Email me : EricLi1235@gmail.com.
 */
public class InnerImplementation {
    public static void main(String[] args) throws Exception{
        A a = InnerA.makeA();
        a.a();
        System.out.println(a.getClass().getCanonicalName());

        HiddenImplementation.callHiddenMethod(a, "a");
        HiddenImplementation.callHiddenMethod(a, "u");
        HiddenImplementation.callHiddenMethod(a, "v");
        HiddenImplementation.callHiddenMethod(a, "w");
    }
}
