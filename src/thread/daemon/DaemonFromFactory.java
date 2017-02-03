package thread.daemon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by EricLi on 2017/1/12.
 * Email me : EricLi1235@gmail.com.
 */
public class DaemonFromFactory implements Runnable {
    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)throws Exception{
        ExecutorService exec = Executors.newFixedThreadPool(10,new DaemonThreadFactory());
        for (int i = 0 ; i < 10 ; i ++){
            exec.execute(new DaemonFromFactory());
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(175);
    }
}
