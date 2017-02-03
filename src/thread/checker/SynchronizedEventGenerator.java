package thread.checker;

/**
 * Created by EricLi on 2017/1/16.
 * Email me : EricLi1235@gmail.com.
 */
public class SynchronizedEventGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    @Override
    public int next() {
        //锁住整个类
        synchronized (SynchronizedEventGenerator.class){
            ++ currentEvenValue;//这里是最危险的
            Thread.yield();
            ++ currentEvenValue;
        }
        return currentEvenValue;
    }

    public static void main(String[] args){
        EventChecker.test(new SynchronizedEventGenerator());
    }
}
