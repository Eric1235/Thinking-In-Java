package thread.cooperation.objectpool;

/**
 * Created by EricLi on 2017/2/15.
 * Email me : EricLi1235@gmail.com.
 */
public class Fat {
    private volatile double d;
    private static int counter = 0;
    private final int id = counter++;

    public Fat(){
        for (int i = 1 ; i < 10000; i ++){
            d += (Math.PI + Math.E) / (double)i;
        }
    }

    public void operation(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Fat id: " + id;
    }
}
