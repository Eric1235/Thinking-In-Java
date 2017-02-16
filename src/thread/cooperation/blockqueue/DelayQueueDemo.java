package thread.cooperation.blockqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.*;

/**
 * Created by EricLi on 2017/2/6.
 * Email me : EricLi1235@gmail.com.
 */
public class DelayQueueDemo {

    static class DelayedTask implements Runnable, Delayed{
        private static int counter = 0;
        private final int id = counter ++;
        private final int delta;//延迟执行时间
        private final long trigger;//执行的时间点
        //保证了任务创建的顺序
        protected static List<DelayedTask> sequence = new ArrayList<>();

        public DelayedTask(int delayMilliseconds){
            delta = delayMilliseconds;
            trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);
            sequence.add(this);
        }

        //延迟有多长时间到期
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
        }

        //进行比较
        @Override
        public int compareTo(Delayed o) {
            DelayedTask that = (DelayedTask)o;
            if(trigger < that.trigger){
                return -1;
            }
            if(trigger > that.trigger){
                return 1;
            }
            return 0;
        }

        //具体要执行的内容
        @Override
        public void run() {
            System.out.print(this + " ");
        }

        @Override
        public String toString() {
            return String.format("[%1$-4d]", delta) + " Task " + id;
        }

        public String summary(){
            return "(" + id + ":" + delta + ")";
        }

        //一种关闭所有事物的途径
        public static class EndSentinel extends DelayedTask{
            private ExecutorService exec ;

            public EndSentinel(int delayMilliseconds, ExecutorService exec) {
                super(delayMilliseconds);
                this.exec = exec;
            }

            @Override
            public void run() {
                for(DelayedTask task : sequence){
                    System.out.print(task.summary() + " ");
                }
                System.out.println();
                System.out.println(this + " Calling shutdownNow()");
                //关闭所有任务
                exec.shutdownNow();
            }
        }
    }

    //自身是一个任务
    static class DelayedTaskConsumer implements Runnable{

        //配合实现了Delayed的接口进行使用
        private DelayQueue<DelayedTask> q;

        public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
            this.q = q;
        }

        //从队列中获取任务执行
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    //如果延时未到，你是不会得到任务去执行的。延时到期才能取得任务
                    q.take().run();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Finished DelayedTaskConsumer");
        }
    }

    public static void main(String[] args){
        Random rand = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> queue = new DelayQueue<>();

        for(int i = 0 ; i < 20 ; i ++){
            //生成延时任务
            queue.put(new DelayedTask(rand.nextInt(5000)));

        }
        queue.add(new DelayedTask.EndSentinel(5000,exec));
        exec.execute(new DelayedTaskConsumer(queue));
    }
}
