package thread.threadlocal;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by EricLi on 2017/1/18.
 * Email me : EricLi1235@gmail.com.
 */
public class ThreadLocalVariableHolder {
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
      private Random rand = new Random(47);
        @Override
        protected Integer initialValue() {
            return rand.nextInt(10000);
        }
    };

    public static void increment(){
        value.set(value.get() + 1);
    }

    public static int get(){
        return value.get();
    }

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0 ; i < 5 ; i ++){
            exec.execute(new Accessor(i));
        }
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();//立马停止
    }


    static class Accessor implements Runnable{
        private final int id;

        public Accessor(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                ThreadLocalVariableHolder.increment();
                Thread.yield();
                System.out.println(toString());
            }
        }

        @Override
        public String toString() {
            return "#" + id + ":" + ThreadLocalVariableHolder.get();
        }
    }



}
