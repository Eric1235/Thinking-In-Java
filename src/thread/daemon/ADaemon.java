package thread.daemon;

import java.util.concurrent.TimeUnit;

/**
 * Created by EricLi on 2017/1/12.
 * Email me : EricLi1235@gmail.com.
 */
public class ADaemon implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Daemon");
            TimeUnit.MILLISECONDS.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("Finally");
        }
    }

    public static void main(String[] args){
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
    }
}
