package thread.cooperation;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by EricLi on 2017/2/18.
 * Email me : EricLi1235@gmail.com.
 */
public class RestaurantWithQueues {

    /**
     * 一个订单
     * 顾客
     * 食物
     * 侍者
     */
    static class Order{
        private static int counter = 0;
        private final int id = counter++;
        private final Customer customer;
        private final WaitPerson waitPerson;
        private final Food food;

        public Order(Customer customer, WaitPerson waitPerson, Food food) {
            this.customer = customer;
            this.waitPerson = waitPerson;
            this.food = food;
        }

        public Food item(){
            return food;
        }

        public Customer getCustomer() {
            return customer;
        }

        public WaitPerson getWaiterPerson() {
            return waitPerson;
        }

        @Override
        public String toString() {
            return "Order: " + id + "item" + food + "for: " + customer + "served by: " + waitPerson;
        }
    }

    /**
     * 厨师那里返回的东西
     * 订单
     * 食物（大概有按照订单来做好）
     * 附加问题，厨师是不是什么菜都能做？
     */
    static class Plate{
        private final Order order;
        private final Food food;

        public Plate(Order order, Food food) {

            this.order = order;
            this.food = food;
        }

        public Order getOrder() {
            return order;
        }

        public Food getFood() {
            return food;
        }

        @Override
        public String toString() {
            return food.toString();
        }
    }

    /**
     * 顾客类
     * 需要有一个侍者来服务
     * 顾客点的食物列表
     */
    static class Customer implements Runnable{
        private static int counter = 0;
        private final int id = counter++;
        private final WaitPerson waiterPerson;
        //一个顾客一条队列
        private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<>();

        public Customer(WaitPerson waiterPerson) {
            this.waiterPerson = waiterPerson;
        }

        //顾客吃东西
        public void deliver(Plate p) throws InterruptedException{
            placeSetting.put(p);
        }

        /**
         * 模拟顾客点餐
         * 有点不符合常理，点一个，上一个，吃一个；再点，再上，再吃
         */
        @Override
        public void run() {
            //大概，一到5个餐吧
            for (Course course : Course.values()){
                //模拟点的食物
                Food food = course.randomSelection();
                try {
                    //顾客点餐
                    waiterPerson.placeOrder(this, food);
                    System.out.println(this + "点了 " + food);
                    //顾客吃饭，中间经历了什么，鬼知道
                    System.out.println(this + " eating " + placeSetting.take());
                }catch (InterruptedException e){
                    System.out.println(this + "interrupted");
                    break;
                }
            }
            System.out.println(this + " finished meal, leaving");
        }

        @Override
        public String toString() {
            return "Customer " + id + " ";
        }
    }

    /**
     * 侍者
     * 他是哪个餐厅的
     * 自己的单子，总数来的
     */
    static class WaitPerson implements Runnable{
        private static int counter = 0;
        private final int id = counter++;
        private final Restaurant restaurant;
        BlockingQueue<Plate> filledOrders =
                new LinkedBlockingQueue<>();//总的单，从厨师那里拿的单

        public WaitPerson(Restaurant restaurant) {
            this.restaurant = restaurant;
        }

        public void placeOrder(Customer customer, Food food){
            try {
                restaurant.orders.put(new Order(customer, this,food));
            }catch (InterruptedException e){

                System.out.println(this + "placeOrder interrupted");
            }
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    Plate plate = filledOrders.take();//从厨师那里，拿到做好的餐
                    System.out.println(this + "received " + plate + " delivering to " +
                    plate.getOrder().getCustomer());
                    //把餐送到顾客那里
                    plate.getOrder().getCustomer().deliver(plate);
                }
            }catch (InterruptedException e){
                System.out.println(this + "interrupted");
            }
            System.out.println(this + "off duty");
        }

        @Override
        public String toString() {
            return "WaitPerson " + id + " ";
        }
    }

    /**
     * 厨师类
     * 他是属于哪个餐馆的
     * 如何处理那些点餐
     */
    static class Chef implements Runnable{
        private static int counter = 0;
        private final int id = counter++;
        private final Restaurant restaurant;
        private static Random rand = new Random(47);

        public Chef(Restaurant restaurant) {
            this.restaurant = restaurant;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    //从餐馆队列里面拿订单
                    Order order = restaurant.orders.take();
                    Food food = order.item();
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                    Plate plate = new Plate(order, food);
                    //做好了，给侍者
                    order.getWaiterPerson().filledOrders.put(plate);
                }
            }catch (InterruptedException e){
                System.out.println(this + "interrupted");
            }
            System.out.println(this + " off duty");
        }

        @Override
        public String toString() {
            return "Chef " + id + " ";
        }
    }

    static class Restaurant implements  Runnable{
        private List<WaitPerson> waiterPersons = new ArrayList<>();
        private List<Chef> chefs = new ArrayList<>();
        private  ExecutorService exec;
        private static Random rand = new Random(47);
        BlockingQueue<Order> orders = new LinkedBlockingQueue<>();

        public Restaurant(ExecutorService e, int nWaitPersons, int nChefs){
            exec = e;
            for (int i = 0 ; i < nWaitPersons; i ++){
                WaitPerson waiterPerson = new WaitPerson(this);
                waiterPersons.add(waiterPerson);
                exec.execute(waiterPerson);
            }
            for (int i = 0 ; i < nChefs ; i ++){
                Chef chef = new Chef(this);
                chefs.add(chef);
                exec.execute(chef);
            }
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    //随机拿一个侍者
                    WaitPerson wp = waiterPersons.get(rand.nextInt(waiterPersons.size()));
                    //去服侍一个顾客
                    Customer c = new Customer(wp);
                    exec.execute(c);
                    TimeUnit.MILLISECONDS.sleep(1000);
                }
            }catch (InterruptedException e){
                System.out.println("Restaurant interrupted");
            }
            System.out.println("Restaurant closing");
        }
    }

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        Restaurant restaurant = new Restaurant(exec, 5, 2);
        exec.execute(restaurant);
        if(args.length > 0){
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        }else {
            System.out.println("Press Enter to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
