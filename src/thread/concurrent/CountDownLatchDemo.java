package thread.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by EricLi on 2017/2/5.
 * Email me : EricLi1235@gmail.com.
 */
//把一个处理过程分成若干处理部分，有些需要进行等待，有些则可以先执行，执行的时候计数器减一，等到为0的时候，等待的部分开始执行
public class CountDownLatchDemo {

    static class TaskPortion implements Runnable{

        private static int counter = 0;
        private final int id = counter++;
        private Random rand = new Random(47);//恰好是线程安全的
        //锁存器，计数
        private final CountDownLatch latch;

        public TaskPortion(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                doWork();
                //完成了以后就减一
                latch.countDown();
            }catch (InterruptedException ex){

            }
        }

        public void doWork() throws InterruptedException{
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
            System.out.println(this + "completed");
        }

        @Override
        public String toString() {
            return String.format("%1$-3d ", id);
        }
    }

    //必须等待的部分
    static class WaitingTask implements Runnable{
        private static int counter = 0;
        private final int id = counter ++;
        private CountDownLatch latch;

        public WaitingTask(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                //直接进行阻塞
                latch.await();
                System.out.println("Latch barrier passed for " + this);
            }catch (InterruptedException e){
                System.out.println(this + "interrupted");
            }
        }

        @Override
        public String toString() {
            return String.format("WaitingTask %1$-3d ", id);
        }
    }

    static final int SIZE = 100;
    public static void main(String[] args)throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();

        //使用同一个对象，有SIZE个任务
        CountDownLatch latch = new CountDownLatch(SIZE);
        for(int i = 0 ; i < 10 ; i ++){
            exec.execute(new WaitingTask(latch));
        }
        //取消阻塞以后，能够执行的顺序也是乱的
        for(int i = 0; i < SIZE; i ++){
            exec.execute(new TaskPortion(latch));
        }
        System.out.println("Launched all tasks");
        exec.shutdown();
    }
}
