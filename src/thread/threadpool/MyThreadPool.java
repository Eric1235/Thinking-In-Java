package thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程
 * @author lixinkun
 * date: 2019-07-31 15:04
 */
public class MyThreadPool {

    public static void main(String[] args) {
        ExecutorService exec = new ThreadPoolExecutor(10, 10,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        //超过的任务就会被拒绝掉
        for(int i = 0 ; i < 500 ; i ++){
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
