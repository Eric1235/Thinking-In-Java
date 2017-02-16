package thread.cooperation;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by EricLi on 2017/2/3.
 * Email me : EricLi1235@gmail.com.
 */
public class ToastOmatic {

    static class ToastQueue extends LinkedBlockingQueue<Toast> {}

    static class Toast{
        public enum Status{DRY, BUTTERED, JANNED}
        private Status status = Status.DRY;
        private final int id;

        public Toast(int id) {
            this.id = id;
        }

        public void buttered(){
            status = Status.BUTTERED;
        }

        public void jam(){
            status = Status.JANNED;
        }

        public Status getStatus() {
            return status;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "Toast " + id + ": " + status;
        }
    }

    static class Toaster implements Runnable{

        private ToastQueue queue;
        private int count = 0;

        private Random random = new Random(47);

        public Toaster(ToastQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));
                    Toast t = new Toast(count ++);
                    System.out.println(t);
                    queue.put(t);
                }
            }catch (InterruptedException e){
                System.out.println("Toaster interrupted");
            }
            System.out.println("Toaster off");
        }
    }

    static class Butterer implements Runnable{

        private ToastQueue dryQueue, butteredQueue;

        public Butterer(ToastQueue dryQueue, ToastQueue butteredQueue) {
            this.dryQueue = dryQueue;
            this.butteredQueue = butteredQueue;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    Toast t = dryQueue.take();
                    t.buttered();
                    System.out.println(t);
                    butteredQueue.put(t);
                }
            }catch (InterruptedException e){
                System.out.println("Butterer interrupted");
            }
            System.out.println("Butterer off");
        }
    }

    static class Jammer implements Runnable{

        private ToastQueue butterdQueue, finshedQueue;

        public Jammer(ToastQueue butterdQueue, ToastQueue finshedQueue) {
            this.butterdQueue = butterdQueue;
            this.finshedQueue = finshedQueue;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    Toast t = butterdQueue.take();
                    t.jam();
                    System.out.println(t);
                    finshedQueue.put(t);
                }
            }catch (InterruptedException e){
                System.out.println("Jammer interrupted");
            }
            System.out.println("Jammer off");
        }
    }

    static class Eater implements Runnable{
        private ToastQueue finishedQueue;
        private int counter = 0;

        public Eater(ToastQueue finishedQueue) {
            this.finishedQueue = finishedQueue;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    Toast t = finishedQueue.take();
                    if(t.getId() != counter++ ||
                            t.getStatus() != Toast.Status.JANNED){
                        System.out.println("Error >>>> " + t);
                        System.exit(1);
                    }else {
                        System.out.println("Chomp! " + t);
                    }
                }
            }catch (InterruptedException e){
                System.out.println("Eater interrupted");
            }
            System.out.println("Eater off");
        }
    }

    public static void main(String[] args) throws Exception{
        ToastQueue dryQueue = new ToastQueue();
        ToastQueue butteredQueue = new ToastQueue();
        ToastQueue finishQueue = new ToastQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butterer(dryQueue,butteredQueue));
        exec.execute(new Jammer(butteredQueue, finishQueue));
        exec.execute(new Eater(finishQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
