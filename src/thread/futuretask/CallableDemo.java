package thread.futuretask;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by EricLi on 2017/1/11.
 * Email me : EricLi1235@gmail.com.
 */
public class CallableDemo {

    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();

        for (int i = 0 ; i < 10 ; i ++){
            //提交到线程池，注意，下面不是使用exec.execute()了
            results.add(exec.submit(new TaskWithResult(i)));
        }

        for (Future<String> fs : results){
            try {
                System.out.println(fs.get());
            }catch (InterruptedException e){
                e.printStackTrace();
                return;
            }catch (ExecutionException e){
                e.printStackTrace();
            }finally {
                exec.shutdown();
            }
        }
    }

    static class TaskWithResult implements Callable<String>{
        private int id;

        public TaskWithResult(int id) {
            this.id = id;
        }

        //在这里处理逻辑，相当于run？
        @Override
        public String call() throws Exception {
            for (int i = 1 ; i < 10000 ; i ++){
                   id += (Math.PI + Math.E) * (double)i;
                }
            return "result of TaskWithResult " + (id);
        }
    }
}
