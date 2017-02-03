package thread.synchronizedblock;

/**
 * Created by EricLi on 2017/1/18.
 * Email me : EricLi1235@gmail.com.
 */
public class PairManipulator implements Runnable {
    private PairManager pairManager;

    public PairManipulator(PairManager pairManager) {
        this.pairManager = pairManager;
    }

    @Override
    public void run() {
        while (true){
            pairManager.increment();
        }
    }

    @Override
    public String toString() {
        return "Pair : " + pairManager.getPair() + "checkCounter = " + pairManager.checkCounter.get() ;
    }
}
