package thread.cooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by EricLi on 2017/1/22.
 * Email me : EricLi1235@gmail.com.
 */

/**
 * 会不会错失信号呢？
 */
public class WaxOMatic {
    static class Car{
        private boolean waxOn = false;
        //打蜡，step2
        public synchronized void waxed(){
            waxOn = true;
            notifyAll();
        }

        //抛光，step1
        public synchronized void buffed(){
            waxOn = false;
            notifyAll();
        }

        //使用这个方法就不会错失信号
        public synchronized void waitForWaxing() throws InterruptedException{
            while (waxOn == false){
                wait();
            }
        }

        public synchronized void waitingForBuffing() throws InterruptedException{
            while (waxOn == true){
                wait();
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
            System.out.println("Ending Wax On Task");
        }
    }

    static class WaxOff implements Runnable{
        private Car c;

        public WaxOff(Car c) {
            this.c = c;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    c.waitForWaxing();
                    System.out.println("Wax Off!");
                    TimeUnit.MILLISECONDS.sleep(200);
                    c.buffed();
                }
            }catch (InterruptedException e){
                System.out.println("Exiting via interrupt");
            }
            System.out.println("Ending Wax Off Task");
        }
    }

    //不断一层一层地打蜡--抛光
    public static void main(String[] args) throws Exception{
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        //在这里，哪个先执行都一样
        exec.execute(new WaxOn(car));
        exec.execute(new WaxOff(car));
        TimeUnit.SECONDS.sleep(5);
        //会调用所有由它控制的线程的interrupt方法
        exec.shutdownNow();
    }

}
