package collection;

/**
 * Created by EricLi on 2016/12/17.
 * Email me : EricLi1235@gmail.com.
 */
public class Groundhog2 extends Groundhog {
    public Groundhog2(int number) {
        super(number);
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Groundhog2 && ((Groundhog2) obj).number == number);
    }
}
