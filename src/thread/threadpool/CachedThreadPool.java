package thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by EricLi on 2017/1/11.
 * Email me : EricLi1235@gmail.com.
 */
public class CachedThreadPool {
    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();//一样数量的线程
        for(int i = 0 ; i < 5 ; i ++){
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
