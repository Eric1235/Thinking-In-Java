package thread.daemon;

import java.util.concurrent.TimeUnit;

/**
 * Created by EricLi on 2017/1/12.
 * Email me : EricLi1235@gmail.com.
 */
public class SimpleDeamons implements Runnable {
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
    public static void main(String[] args) throws Exception{
        for (int i = 0 ; i < 10 ; i ++){
            Thread deamon = new Thread(new SimpleDeamons());
            deamon.setDaemon(true);//就是这一句
            deamon.start();
        }
        System.out.println("All deamons started");
        TimeUnit.MILLISECONDS.sleep(175);
    }
}
