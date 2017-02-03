package genericity.border;

/**
 * Created by EricLi on 2016/12/11.
 * Email me : EricLi1235@gmail.com.
 */
public class BasicBounds {
    public static void main(String[] args){
        Solid<Bounded> solid = new Solid<>(new Bounded());
        solid.color();
        solid.getWeight();
        solid.getItem();
    }
}
