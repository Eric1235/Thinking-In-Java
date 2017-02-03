package genericity.erase_t;

/**
 * Created by EricLi on 2016/12/10.
 * Email me : EricLi1235@gmail.com.
 */
public class Manipulation {
    public static void main(String[] args){
        HasF f = new HasF();
        Manipulator<HasF> manipulator = new Manipulator<>(f);
        manipulator.manipulate();
    }
}
