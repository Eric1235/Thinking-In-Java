package thread.cooperation;

/**
 * Created by EricLi on 2017/1/23.
 * Email me : EricLi1235@gmail.com.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 生产者和消费者问题
 */
public class Restaurant {

    Meal meal;
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);
    ExecutorService exec = Executors.newCachedThreadPool();

    public Restaurant(){
        exec.execute(waitPerson);
        exec.execute(chef);
    }

    class Meal{
        private final int orderNum;

        public Meal(int orderNum) {
            this.orderNum = orderNum;
        }

        @Override
        public String toString() {
            return "Meal " + orderNum;
        }
    }

    class WaitPerson implements Runnable{
        private Restaurant restaurant;

        public WaitPerson(Restaurant restaurant) {
            this.restaurant = restaurant;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    synchronized (this){
                        while (restaurant.meal == null){
                            wait();
                        }
                    }
                    System.out.println("WaitPerson got " + restaurant.meal);
                    synchronized (restaurant.chef){
                        restaurant.meal = null;
                        restaurant.chef.notify();
                    }
                }
            }catch (InterruptedException e){
                System.out.println("WaitPerson interrupted");
            }
        }
    }

    class Chef implements Runnable{
        private Restaurant restaurant;

        private int count = 0;

        public Chef(Restaurant restaurant) {
            this.restaurant = restaurant;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    synchronized (this){
                        while (restaurant.meal != null){
                            wait();
                        }
                    }
                    if(++count == 10){
                        System.out.println("Out of food, waiting");
//                        wait();//当前线程不是所有者
                        restaurant.exec.shutdownNow();
                    }
                    System.out.println("Order up!");
                    synchronized (restaurant.waitPerson){
                        restaurant.meal = new Meal(count);
                        restaurant.waitPerson.notify();
                    }
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            }catch (InterruptedException e){
                System.out.println("chef interrupted");
            }
        }
    }

    public static void main(String[] args){
        new Restaurant();
    }
}
