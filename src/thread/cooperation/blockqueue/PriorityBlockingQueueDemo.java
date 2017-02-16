package thread.cooperation.blockqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by EricLi on 2017/2/6.
 * Email me : EricLi1235@gmail.com.
 */
public class PriorityBlockingQueueDemo {

    static class PrioritizedTask implements Runnable, Comparable<PrioritizedTask>{
        private Random rand = new Random(47);
        private static int counter = 0;
        private final int id = counter ++;
        private final int priority;
        //保存对象
        protected static List<PrioritizedTask> sequence = new ArrayList<>();

        public PrioritizedTask(int priority) {
            this.priority = priority;
            sequence.add(this);
        }

        //比较优先级
        @Override
        public int compareTo(PrioritizedTask o) {
            return priority < o.priority ? 1 : (priority > o.priority ? -1 : 0);
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(250));
            }catch (InterruptedException e){

            }
            System.out.println(this);
        }

        @Override
        public String toString() {
            return String.format("[%1$-3d]", priority) + " Task " + id;
        }

        public String summary(){
            return "(" + id + ":" + priority + ")";
         }

         public static class EndSentinel extends PrioritizedTask{
            private ExecutorService exec = Executors.newCachedThreadPool();

             public EndSentinel(ExecutorService exec) {
                 //优先级是最低的
                 super(-1);
                 this.exec = exec;
             }

             @Override
             public void run() {
                 int count = 0;
                 for(PrioritizedTask task : sequence){
                     System.out.print(task.summary());
                     if(++ count % 5 == 0 ){
                         System.out.println();
                     }
                 }
                 System.out.println();
                 System.out.println(this + " Calling shutdownNow()");
                 exec.shutdownNow();
             }
         }
    }

    //多种生产Task的方式
    static class PrioritizedTaskProducer implements Runnable{
        private Random rand = new Random(47);
        private Queue<Runnable> queue;
        private ExecutorService exec = Executors.newCachedThreadPool();

        public PrioritizedTaskProducer(Queue<Runnable> queue, ExecutorService exec) {
            this.queue = queue;
            this.exec = exec;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20 ; i ++){
                queue.add(new PrioritizedTask(rand.nextInt(10)));
                Thread.yield();
            }
            try {
                for(int i = 0 ; i < 10 ; i ++){
                    TimeUnit.MILLISECONDS.sleep(250);
                    queue.add(new PrioritizedTask(rand.nextInt(10)));
                }
                for (int i = 0 ; i < 10 ; i ++){
                    queue.add(new PrioritizedTask(i));
                }
                queue.add(new PrioritizedTask.EndSentinel(exec));
            }
            catch (InterruptedException e){

            }
            System.out.println("Finished PrioritizedTaskProducer");
        }
    }

    //不断地往队列拿对象，没有就阻塞
    static class PrioritizedTaskConsumer implements Runnable{

        private PriorityBlockingQueue<Runnable> q;

        public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> q) {
            this.q = q;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    q.take().run();
                }
            }catch (InterruptedException e){

            }
            System.out.println("Finished PrioritizedTaskConsumer");
        }
    }

    public static void main(String[] args) throws Exception{
        Random random = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();
        //通过queue进行彼此连接
        exec.execute(new PrioritizedTaskProducer(queue, exec));
        exec.execute(new PrioritizedTaskConsumer(queue));
    }
}
