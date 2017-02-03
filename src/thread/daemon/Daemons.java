package thread.daemon;

import java.util.concurrent.TimeUnit;

/**
 * Created by EricLi on 2017/1/12.
 * Email me : EricLi1235@gmail.com.
 */

public class Daemons {
    static class Daemon implements Runnable {
        private Thread[] t = new Thread[10];

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                t[i] = new Thread(new DaemonSpawn());
                t[i].start();
                System.out.println("Daemons " + i + " start");
            }
            for (int i = 0; i < t.length; i++) {
                System.out.println("t[" + i + "].siDaemon()=" + t[i].isDaemon());
            }
            while (true) {
                Thread.yield();
            }
        }
    }

    static class DaemonSpawn implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread() + " " + this);
                Thread.yield();
            }
        }
    }

    public static void main(String[] args)throws Exception{
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.println("d.isDaemon() = " + d.isDaemon() );
        TimeUnit.MILLISECONDS.sleep(1);
    }

}



