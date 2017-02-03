package genericity.generator;

/**
 * Created by EricLi on 2016/12/8.
 * Email me : EricLi1235@gmail.com.
 */
public class Coffee {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}
