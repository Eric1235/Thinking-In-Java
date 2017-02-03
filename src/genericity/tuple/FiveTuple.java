package genericity.tuple;

/**
 * Created by EricLi on 2016/12/7.
 * Email me : EricLi1235@gmail.com.
 */
public class FiveTuple<A, B, C, D, E> extends FourTuple<A, B, C, D> {
    public final E fifth;
    public FiveTuple(A a, B b, C c, D d, E e){
        super(a, b, c, d);
        fifth = e;
    }

    @Override
    public String toString() {
        return "FiveTuple{" +
                "fifth=" + fifth +
                '}';
    }
}
