package thread.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by EricLi on 2017/1/20.
 * Email me : EricLi1235@gmail.com.
 */

/**
 * 显示不同类型的阻塞
 */
public class Interrupting {

    static class SleepBlocked implements Runnable{

        @Override
        public void run() {
            try{
                TimeUnit.SECONDS.sleep(100);
            }catch (InterruptedException e){
                System.out.println("InterruptedException");
            }
            System.out.print("Exiting SleepBlocked.run()");
        }
    }

    /**
     * 不可中断的阻塞
     */
    static class IOBlocked implements Runnable{
        private InputStream in;

        public IOBlocked(InputStream in) {
            this.in = in;
        }

        @Override
        public void run() {
            try {
                System.out.println("Waiting for read():");
                in.read();
            }catch (IOException e){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Interrupted from blocked I/O");
                }else {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Exiting IOBlocked.run()");
        }
    }

    /**
     * 不可中断的阻塞
     */
    static class SynchronizedBlocked implements  Runnable{
        public synchronized void f(){
            while (true){
                Thread.yield();
            }
        }

        public SynchronizedBlocked() {
            new Thread(){
                @Override
                public void run() {
                    f();//获取对象的锁，并且永远不会释放
                }
            }.start();
        }

        @Override
        public void run() {
            System.out.println("Trying to call f()");
            f();//等待锁被释放
            System.out.println("Exiting SynchronizeBlocked.run()");
        }
    }

    private static ExecutorService exec = Executors.newCachedThreadPool();
    static void test(Runnable r)throws InterruptedException{
        Future<?> f = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Interrupting " + r.getClass().getName());
        f.cancel(true);
        System.out.println("Interrupt sent to " + r.getClass().getName());
    }

    public static void main(String[] args)throws Exception{
        test(new SleepBlocked());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        System.out.println("Aborting with System.exit(0)");
        System.exit(0);
    }
}
