package thread.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lixinkun
 * date: 2019-07-23 15:34
 */
public class NotSafeThread implements Runnable {

    public static Number number = new Number();

    public static int i = 0;

    @Override
    public void run() {
        number.setNum(i++);
        value.set(number);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
        }
        System.out.println(value.get().getNum());
    }

    public static ThreadLocal<Number> value = new ThreadLocal<Number>() {
    };

    public static void main(String[] args) {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            newCachedThreadPool.execute(new NotSafeThread());
        }
        newCachedThreadPool.shutdown();
    }
}
