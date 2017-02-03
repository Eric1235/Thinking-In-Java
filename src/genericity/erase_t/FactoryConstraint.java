package genericity.erase_t;

/**
 * Created by EricLi on 2016/12/10.
 * Email me : EricLi1235@gmail.com.
 */
public class FactoryConstraint {

    //传递Class<T>
    public static void main(String[] args){
        new Foo2<>(new IntegerFactory());
        new Foo2<>(new Widget.Factory());
    }
}
