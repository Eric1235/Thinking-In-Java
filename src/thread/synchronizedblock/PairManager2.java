package thread.synchronizedblock;

/**
 * Created by EricLi on 2017/1/18.
 * Email me : EricLi1235@gmail.com.
 */
public class PairManager2 extends PairManager {
    @Override
    public void increment() {
        Pair tmp = new Pair();
        synchronized (this){
            pair.incrementX();
            pair.incrementY();
            tmp = getPair();
        }

        store(tmp);
    }
}
