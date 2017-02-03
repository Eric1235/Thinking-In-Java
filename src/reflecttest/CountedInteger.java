package reflecttest;

/**
 * Created by EricLi on 2016/11/29.
 * Email me : EricLi1235@gmail.com.
 */
public class CountedInteger {
    private static long counter;//因为这个量是静态的，所以可以不断进行累加
    private final long id = counter ++;
    public String toString(){
        return Long.toString(id);
    }
}
