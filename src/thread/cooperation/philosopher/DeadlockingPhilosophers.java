package thread.cooperation.philosopher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by EricLi on 2017/2/5.
 * Email me : EricLi1235@gmail.com.
 */
public class DeadlockingPhilosophers {
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

        for (int i = 0 ; i < size ; i ++){
            //等到每个人各自拿到一只筷子的时候，死锁就产生了。有闭环出现
            exec.execute(new Philosopher(chopsticks[i], chopsticks[(i + 1) % size], i ,ponder));
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
