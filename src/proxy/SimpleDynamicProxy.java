package proxy;

import java.lang.reflect.Proxy;

/**
 * Created by EricLi on 2016/12/5.
 * Email me : EricLi1235@gmail.com.
 */
public class SimpleDynamicProxy {
    public static void consumer(Interface iface){
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args){
        RealObject realObject = new RealObject();
        consumer(realObject);

        //创建动态代理
        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.
                getClassLoader(),new Class[]{Interface.class},new DynamicProxyHandler(realObject));
        consumer(proxy);

    }
}
