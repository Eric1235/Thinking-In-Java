package thread.cooperation.objectpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by EricLi on 2017/2/15.
 * Email me : EricLi1235@gmail.com.
 */
public class SemaphoreDemo {
    static class CheckoutTask<T> implements Runnable{
        private static int counter = 0;
        private final int id = counter++;
        private Pool<T> pool;

        public CheckoutTask(Pool<T> pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            try {
                T item = pool.checkOut();
                System.out.println(this + "check out: " + item);
                TimeUnit.SECONDS.sleep(1);
                System.out.println(this + "check in: " + item);
                pool.checkIn(item);
            }catch (Exception e){

            }
        }

        @Override
        public String toString() {
            return "CheckoutTask " + id + " ";
        }
    }

    final static int SIZE = 25;
    public static void main(String[] args) throws Exception{
        final Pool<Fat> pool = new Pool<>(Fat.class, SIZE);
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0 ; i < SIZE ; i ++){
            exec.execute(new CheckoutTask<>(pool));
        }
        System.out.println("All Task created!");
        List<Fat> list = new ArrayList<>();
        for (int i = 0 ; i < SIZE ; i ++){
            Fat f = pool.checkOut();
            System.out.println("main checkout " + i );
            f.operation();
            list.add(f);
        }

        Future<?> blocked = exec.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    pool.checkOut();//如果对象不能签出，那么该方法就会被阻塞住
                    //不过，这里貌似不能签出任何对象
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true);
        System.out.println("Checking in objects in" + list);
        for (Fat f : list){
            pool.checkIn(f);
        }
        exec.shutdown();
    }
}
