package proxy;

/**
 * Created by EricLi on 2016/12/5.
 * Email me : EricLi1235@gmail.com.
 */
public class RealObject implements Interface {
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void somethingElse(String s) {
        System.out.println("SomethingElse " + s);
    }
}
