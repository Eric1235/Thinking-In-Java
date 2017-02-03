package genericity.tuple;

/**
 * Created by EricLi on 2016/12/7.
 * Email me : EricLi1235@gmail.com.
 */
public class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {
    public final D fourth;
    public FourTuple(A a, B b, C c, D d){
        super(a,b,c);
        fourth = d;
    }

    @Override
    public String toString() {
        return "FourTuple{" +
                "fourth=" + fourth +
                '}';
    }
}
