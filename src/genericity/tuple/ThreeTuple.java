package genericity.tuple;

/**
 * Created by EricLi on 2016/12/7.
 * Email me : EricLi1235@gmail.com.
 */
public class ThreeTuple<A,B,C> extends TwoTuple<A,B> {
    public final C third;
    public ThreeTuple(A a, B b, C c){
        super(a,b);
        third = c;
    }

    @Override
    public String toString() {
        return "ThreeTuple{" +
                "third=" + third +
                '}';
    }
}
