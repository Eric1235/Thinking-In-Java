package genericity.tuple;

/**
 * Created by EricLi on 2016/12/7.
 * Email me : EricLi1235@gmail.com.
 */
public class TwoTuple <A, B> {
    public final A first;//指定为final的原因，就是希望产生的对象是不可变的，来保护public元素
    public final B second;
    public TwoTuple(A a, B b){
        first = a;
        second = b;
    }

    @Override
    public String toString() {
        return "TwoTuple{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
