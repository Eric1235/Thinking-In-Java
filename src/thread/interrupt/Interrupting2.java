package thread.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by EricLi on 2017/1/22.
 * Email me : EricLi1235@gmail.com.
 */
public class Interrupting2 {

    static class BlockedMutex{
        private Lock lock = new ReentrantLock();

        public BlockedMutex(){
            lock.lock();
        }

        public void f(){
            try {
                /**
                 * Acquires the lock unless the current thread is
                 * {@linkplain Thread#interrupt interrupted}.
                 */
                lock.lockInterruptibly();
                System.out.println("lock acquired in f()");
            }catch (InterruptedException e){
                System.out.println("Interrupted from lock acquisition in f()");
            }
        }
    }

    static class Block2 implements Runnable{
        BlockedMutex blocked = new BlockedMutex();
        @Override
        public void run() {
            System.out.println("Waiting for f() in BlockedMutex");
            blocked.f();
            System.out.println("Broken out of blocked call");
        }
    }

    public static void main(String[] args)throws Exception{
        Thread t = new Thread(new Block2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt()");
        t.interrupt();
    }
}
