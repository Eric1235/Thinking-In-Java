package thread.cooperation.philosopher;

/**
 * Created by EricLi on 2017/2/5.
 * Email me : EricLi1235@gmail.com.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 打破循环等待，解决死锁问题
 */
public class FixedDiningPhilosopher {
    public static void main(String[] args) throws Exception{
        int ponder = 5;
        if(args.length > 0){
            ponder = Integer.parseInt(args[0]);
        }
        int size = 5;
        if(args.length > 1){
            size = Integer.parseInt(args[1]);
        }

        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] chopsticks = new Chopstick[size];
        for(int i = 0 ; i < size ; i ++){
            chopsticks[i] = new Chopstick();
        }

        //通过不同的拿起方式，打破循环等待
        for (int i = 0 ; i < size ; i ++){
            if(i < size -1){
                //前面哲学家先拿起右边，再拿起左边
                exec.execute(new Philosopher(chopsticks[i], chopsticks[i + 1], i, ponder));
            }else {
                //最后一个哲学家拿到的左边，再到右边
                exec.execute(new Philosopher(chopsticks[0], chopsticks[i], i ,ponder));
//                exec.execute(new Philosopher(chopsticks[i], chopsticks[0], i ,ponder));
            }

        }

        if (args.length == 3 && args[2].endsWith("timeout")){
            TimeUnit.SECONDS.sleep(5);
        }else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
