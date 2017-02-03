package interfacetest;

/**
 * Created by EricLi on 2016/11/16.
 * Email me : EricLi1235@gmail.com.
 */
public class Client {
    public static void main(String[] args){
        A a = new Test();
        System.out.println(a.A(5));
        C c = new Test();
        System.out.println(c.A(5));
        System.out.println(c.C(5));

    }
}
