package thread.cooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by EricLi on 2017/1/23.
 * Email me : EricLi1235@gmail.com.
 */
public class WaxOMatic2 {

    static class Car{
        private boolean waxOn = false;
        private Lock lock = new ReentrantLock();
        //管理任务间的通信，但是不包含任何有关处理状态的信息
        private Condition condition = lock.newCondition();

        public void waxed(){
            lock.lock();
            try {
                waxOn = true;
                condition.signalAll();
            }finally {
                lock.unlock();
            }
        }

        public void buffed(){
            lock.lock();
            try {
                waxOn = false;
                condition.signalAll();
            }finally {
                lock.unlock();
            }
        }

        //请求wax的处理状态
        public void waitingForWaxing() throws InterruptedException{
            lock.lock();
            try {
                while (waxOn == false){
                    condition.await();
                }
            }finally {
                lock.unlock();
            }

        }

        //同样请求wax的处理状态
        public void waitingForBuffing() throws InterruptedException{
            lock.lock();
            try {
                while (waxOn == true){
                    condition.await();
                }
            }finally {
                lock.unlock();
            }
        }
    }

    static class WaxOn implements Runnable{
        private Car car;

        public WaxOn(Car car) {
            this.car = car;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    System.out.println("Wax On!");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                    car.waitingForBuffing();
                }
            }catch (InterruptedException e){
                System.out.println("Exiting via interrupt");
            }
            System.out.println("Exiting Wax on Task");
        }
    }

    static class WaxOff implements Runnable{
        private Car car;

        public WaxOff(Car car) {
            this.car = car;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    car.waitingForWaxing();
                    System.out.println("Wax Off!");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.buffed();
                }
            }catch (InterruptedException e){
                System.out.println("Exiting via interrupt");
            }
            System.out.println("Exiting Wax off Task");
        }
    }

    public static void main(String[] args) throws Exception{
        Car c = new Car();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new WaxOn(c));
        executorService.execute(new WaxOff(c));
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
}
