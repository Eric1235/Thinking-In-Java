package functionaltest;

/**
 * Created by EricLi on 2016/12/18.
 * Email me : EricLi1235@gmail.com.
 */
public abstract class Test<C> {

    String name;

    public Test(String name) {
        this.name = name;
    }

    abstract int test(C container, TestParam tp);
}
