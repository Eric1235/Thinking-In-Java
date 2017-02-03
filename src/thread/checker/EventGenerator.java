package thread.checker;

/**
 * Created by EricLi on 2017/1/16.
 * Email me : EricLi1235@gmail.com.
 */

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 这样运行是不行啦
 */
public class EventGenerator extends IntGenerator {
//    private int currentEvenValue = 0;

    private AtomicInteger currentEvenValue = new AtomicInteger(0);
    @Override
    public synchronized int next() {

        return currentEvenValue.addAndGet(2);
    }

    public static void main(String[] args){
        EventChecker.test(new EventGenerator());
    }
}
