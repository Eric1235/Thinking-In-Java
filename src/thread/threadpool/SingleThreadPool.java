package thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by EricLi on 2017/1/11.
 * Email me : EricLi1235@gmail.com.
 */
public class SingleThreadPool {
    public static void main(String[] args){
        ExecutorService exec = Executors.newSingleThreadExecutor();//排队排队
        for(int i = 0 ; i < 30 ; i ++){
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
