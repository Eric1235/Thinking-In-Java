package thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by EricLi on 2017/1/16.
 * Email me : EricLi1235@gmail.com.
 */

/**
 * 如果你正在写一个变量，它可能接下来被另一个线程读取，或者正在读取一个上一次已经被另一个线程写过的变量
 * 那么你就必须使用同步，并且，读写线程都必须使用相同的监视锁同步
 */
public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();
    public void untimed(){
        boolean capture = lock.tryLock();
        try {
            System.out.println("tryLock() : " + capture);
        }finally {
            if(capture){
                lock.unlock();
            }
        }
    }

    public void timed(){
        boolean capture = false;
        try {
            capture = lock.tryLock(2, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        try {
            System.out.println("tryLock(2,TimeUnit.SECONDS) :" + capture);

        }finally {
            if (capture){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args){
        final AttemptLocking al = new AttemptLocking();
        al.untimed();
        al.timed();
        new Thread(){
            {setDaemon(true);}
            @Override
            public void run() {
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();

        Thread.yield();
        //接下来就不能获得锁了啦
        al.untimed();
        al.timed();
    }
}
