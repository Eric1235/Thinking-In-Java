package proxy;

/**
 * Created by EricLi on 2016/12/6.
 * Email me : EricLi1235@gmail.com.
 */
public class InterfaceViolation {
    public static void main(String[] args){
        A a = new B();
        a.a();
        System.out.println(a.getClass().getCanonicalName());
        if(a instanceof B){
            B b = (B) a;//就是又把类型给转回去了，怎么办呢？
            b.b();
        }
    }
}
