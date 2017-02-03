package thread.checker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by EricLi on 2017/1/16.
 * Email me : EricLi1235@gmail.com.
 */
public class EventChecker implements Runnable {

    private IntGenerator generator;
    private final int id;

    public EventChecker(IntGenerator generator, int id) {
        this.generator = generator;
        this.id = id;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()){
            int val = generator.next();
            if(val % 2 != 0){
                System.out.println(val + " not even");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator generator, int count){
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0 ; i < count ; i ++){
            exec.execute(new EventChecker(generator, i));
        }
        exec.shutdown();
    }

    public static void test(IntGenerator generator){
        test(generator, 10);
    }
}
