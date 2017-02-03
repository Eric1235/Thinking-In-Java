package thread.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by EricLi on 2017/1/13.
 * Email me : EricLi1235@gmail.com.
 */
public class CaptureUncaughtException {

    static class ExceptionThread2 implements Runnable{
        @Override
        public void run() {
            Thread t = Thread.currentThread();
            System.out.println("run() by " + t);
            System.out.println("eh = " + t.getUncaughtExceptionHandler());
            throw new RuntimeException();
        }
    }

    /**
     * 通过这货来捕获异常
     */
    static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("caught " + e);
        }
    }

    static class HandlerThreadFactory implements ThreadFactory{
        @Override
        public Thread newThread(Runnable r) {
            System.out.println(this + " creating new Thread");
            Thread t = new Thread(r);
            System.out.println("created " + t);
            t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());//设置处理机制
            System.out.println("eh = " + t.getUncaughtExceptionHandler());
            return t;
        }
    }

    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
        exec.execute(new ExceptionThread2());
    }
}
