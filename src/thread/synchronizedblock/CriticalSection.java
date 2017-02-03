package thread.synchronizedblock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by EricLi on 2017/1/18.
 * Email me : EricLi1235@gmail.com.
 */
public class CriticalSection {

    static void testApproaches(PairManager pm1, PairManager pm2){
        ExecutorService exec = Executors.newCachedThreadPool();
        PairManipulator p1 = new PairManipulator(pm1);
        PairManipulator p2 = new PairManipulator(pm2);

        PairChecker c1 = new PairChecker(pm1);
        PairChecker c2 = new PairChecker(pm2);

        exec.execute(p1);
        exec.execute(p2);
        exec.execute(c1);
        exec.execute(c2);
        try {
            TimeUnit.SECONDS.sleep(500);
        }catch (InterruptedException e){
            System.out.println("Sleep Interrupted");
        }

        System.out.println("pm1 : " + pm1 + "\npm2 :" + pm2 );
        System.exit(0);
    }

    public static void main(String[] args){
        PairManager pm1 = new PairManager1();
        PairManager pm2 = new PairManager2();
        testApproaches(pm1, pm2);
    }
}
