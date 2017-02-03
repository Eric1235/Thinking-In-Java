package proxy;

/**
 * Created by EricLi on 2016/12/5.
 * Email me : EricLi1235@gmail.com.
 */
public class SimpleProxyDemo {
    public static void main(String[] args){
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }

    public static void consumer(Interface iface){
        iface.doSomething();
        iface.somethingElse("bonobo");
    }
}
