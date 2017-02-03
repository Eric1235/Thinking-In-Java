package proxy;

/**
 * Created by EricLi on 2016/12/5.
 * Email me : EricLi1235@gmail.com.
 */
public class SimpleProxy implements Interface {

    private Interface anInterface;

    public SimpleProxy(Interface anInterface){
        this.anInterface = anInterface;
    }

    @Override
    public void doSomething() {
        System.out.println("SimpleProxy doSomething");
        anInterface.doSomething();
    }

    @Override
    public void somethingElse(String s) {
        System.out.println("SimpleProxy something else  " + s);
        anInterface.somethingElse(s);
    }
}
