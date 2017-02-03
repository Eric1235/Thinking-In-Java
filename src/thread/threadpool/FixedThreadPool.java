package thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by EricLi on 2017/1/11.
 * Email me : EricLi1235@gmail.com.
 */
public class FixedThreadPool {
    public static void main(String[] args){
        ExecutorService exec = Executors.newFixedThreadPool(5);//指定数量的线程，多了就排队，一批一批地换？
        for(int i = 0 ; i < 30 ; i ++){
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
