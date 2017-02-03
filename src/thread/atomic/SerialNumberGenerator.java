package thread.atomic;

/**
 * Created by EricLi on 2017/1/18.
 * Email me : EricLi1235@gmail.com.
 */
public class SerialNumberGenerator {
    private static volatile int s = 0;
    public static synchronized int nextSerialNumber(){
        return s ++;
    }
}
