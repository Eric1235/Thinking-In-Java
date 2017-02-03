package thread.atomic;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by EricLi on 2017/1/18.
 * Email me : EricLi1235@gmail.com.
 */
public class AtomicityTest implements Runnable{
//    private static volatile int i = 0;

    private AtomicInteger i = new AtomicInteger(0);

    //去掉synchronized关键字，性能会好很多
    public synchronized int getValue(){
        //这个虽然是原子性操作，但是缺少同步，也是不稳定状态读取到的数值
//        return i;
        return i.get();
    }

    private synchronized void evenIncrement(){
//        i ++;
//        i ++;
        i.addAndGet(2);
    }

    @Override
    public void run() {
        while (true){
            evenIncrement();
        }
    }

    public static void main(String[] args){

        AtomicityTest atomicityTest = new AtomicityTest();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Aborting");
                int val = atomicityTest.getValue();
                System.out.println(val);
                System.exit(0);
            }
        },5000);

        ExecutorService exec = Executors.newCachedThreadPool();

        exec.execute(atomicityTest);
        while (true){
            int val = atomicityTest.getValue();
            if(val % 2 != 0){
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
