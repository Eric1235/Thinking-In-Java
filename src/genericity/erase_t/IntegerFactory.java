package genericity.erase_t;

/**
 * Created by EricLi on 2016/12/10.
 * Email me : EricLi1235@gmail.com.
 */
public class IntegerFactory implements FactoryI<Integer>{
    @Override
    public Integer create() {
        return new Integer(9);
    }
}
