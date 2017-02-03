package thread.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by EricLi on 2017/1/13.
 * Email me : EricLi1235@gmail.com.
 */

/**
 * 使用try-catch块包住main，照样不能捕获线程的异常哦
 */
public class NaiveExceptionHandling {
    public static void main(String[] args){
        try{
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
        }catch (RuntimeException e){
            System.out.println("Exception has been handled");
        }
    }
}
