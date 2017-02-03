package thread.synchronizedblock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by EricLi on 2017/1/18.
 * Email me : EricLi1235@gmail.com.
 */
public class ExplicitPairManager1 extends PairManager {
    private Lock lock = new ReentrantLock();
    @Override
    public void increment() {
        lock.lock();
        try {
            pair.incrementX();
            pair.incrementY();
        }finally {
            lock.unlock();
        }
        store(getPair());
    }

}
