package thread.cooperation;

import thread.threadpool.LiftOff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by EricLi on 2017/1/23.
 * Email me : EricLi1235@gmail.com.
 */
public class TestBlockingQueues {

    static class LiftOffRunner implements Runnable{

        //阻塞队列
        private BlockingQueue<LiftOff> rockets;

        public LiftOffRunner(BlockingQueue<LiftOff> rockets) {
            this.rockets = rockets;
        }

        public void add(LiftOff lo){
//            try {
//                rockets.add(lo);
//            }catch (InterruptedException e){
//                System.out.println("Interrupted during input");
//            }
            rockets.add(lo);
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    //从阻塞队列中获得一个线程
                    LiftOff rocket = rockets.take();
                    rocket.run();
                }
            }catch (InterruptedException e){
                System.out.println("Waiting from take()");
            }
            System.out.println("Exiting LiftOffRunner");
        }
    }

    static void getKey(){
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    static void getKey(String msg){
        System.out.println(msg);
        getKey();
    }

    static void test(String msg, BlockingQueue<LiftOff> queue){
        System.out.println(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        Thread t = new Thread(runner);
        t.start();
        for (int i = 0 ; i < 5 ; i ++){
            runner.add(new LiftOff());
        }
        getKey("Press 'Enter' (" + msg + ")");
        t.interrupt();
        System.out.println("Finished " + msg + " test");
    }

    public static void main(String[] args){
        test("LinkedBlockingQueue", new LinkedBlockingQueue<>());
        test("ArrayBlockingQueue", new ArrayBlockingQueue<>(3));
        test("SynchronousQueue", new SynchronousQueue<>());
    }

}
