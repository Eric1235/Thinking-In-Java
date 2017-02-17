package thread.emulation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by EricLi on 2017/2/16.
 * Email me : EricLi1235@gmail.com.
 */
public class BankTellerSimulation {

    static class Customer{
        private final int serviceTime;

        public Customer(int serviceTime) {
            this.serviceTime = serviceTime;
        }

        public int getServiceTime() {
            return serviceTime;
        }

        @Override
        public String toString() {
            return "["+ serviceTime +
                    "]";
        }
    }

    //顾客队列
    static class CustomerLine extends ArrayBlockingQueue<Customer>{
        public CustomerLine(int capacity) {
            super(capacity);
        }

        @Override
        public String toString() {
            if(size() == 0){
                return "[Empty]";
            }
            StringBuilder result = new StringBuilder();
            for (Customer c : this){
                result.append(c);
            }
            return result.toString();
        }
    }

    //随机增加顾客
    static class CustomerGenerator implements Runnable{
        private CustomerLine line;

        private static Random rand = new Random(47);

        public CustomerGenerator(CustomerLine line) {
            this.line = line;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
                    line.put(new Customer(rand.nextInt(1000)));
                }
            }catch (InterruptedException e){
                System.out.println("CustomerGenerator interrupted!");
            }
            System.out.println("CustomerGenerator terminating");
        }
    }

    static class Teller implements Runnable, Comparable<Teller>{
        private static int counter = 0;
        private final int id = counter++;
        private int customerServed = 0;
        private CustomerLine customers;
        private boolean servingCustomerLine = true;

        public Teller(CustomerLine customers) {
            this.customers = customers;
        }

        @Override
        public synchronized int compareTo(Teller o) {
            return customerServed < o.customerServed ? -1 : (customerServed == o.customerServed ? 0 : 1);
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    Customer customer = customers.take();
                    //业务员需要处理这么长的时间啦
                    TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                    synchronized (this){
                        customerServed++;
                        while (!servingCustomerLine){
                            wait();
                        }
                    }
                }
            }catch (InterruptedException e){
                System.out.println(this + "interrupted!");
            }
            System.out.println(this + " terminating");
        }

        //分配去做其他事情
        public synchronized void doSomethingElse(){
            customerServed = 0;
            servingCustomerLine = false;
        }

        //继续回来服务
        public synchronized void serveCustomerLine(){
            assert !servingCustomerLine:"already serving: " + this;
            servingCustomerLine = true;
            notifyAll();
        }

        @Override
        public String toString() {
            return "Teller " + id;
        }

        public String shortString(){
            return "T" + id;
        }
    }

    /**
     * 管理出纳员，对出纳员进行调度
     * 出纳员有两种状态，一种就是正在服务客人，一种就是去做其他的事情
     */
    static class TellerManager implements Runnable{
        private ExecutorService exec;
        private CustomerLine customers;
        private PriorityBlockingQueue<Teller> workingTellers = new PriorityBlockingQueue<>();
        private Queue<Teller> tellersDoingOtherThing = new LinkedList<>();
        private int adjustmentperiod;
        private static Random rand = new Random(47);

        public TellerManager(ExecutorService exec, CustomerLine customers, int adjustmentperiod) {
            this.exec = exec;
            this.customers = customers;
            this.adjustmentperiod = adjustmentperiod;
            Teller teller = new Teller(customers);
            exec.execute(teller);
            workingTellers.add(teller);
        }

        //调控出纳员的数目
        public void adjustTellerNumber(){
            //太忙
            if(customers.size() / workingTellers.size() > 2){
                if (tellersDoingOtherThing.size() > 0){
                    Teller teller = tellersDoingOtherThing.remove();
                    teller.serveCustomerLine();
                    workingTellers.offer(teller);
                    return;
                }
                Teller teller = new Teller(customers);
                exec.execute(teller);
                workingTellers.add(teller);
                return;
            }
            if (workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2){
                reassingOneTeller();
            }

            if(customers.size() == 0){
                while (workingTellers.size() > 1){
                    reassingOneTeller();
                }
            }
        }

        public void reassingOneTeller(){
            Teller teller = workingTellers.poll();
            teller.doSomethingElse();
            tellersDoingOtherThing.offer(teller);
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    TimeUnit.MILLISECONDS.sleep(adjustmentperiod);
                    adjustTellerNumber();
                    System.out.println(customers + "{");
                    for (Teller teller : workingTellers){
                        System.out.print(teller.shortString() + " ");
                    }
                    System.out.println("}");
                }
            }catch (InterruptedException e){
                System.out.println(this + "interrupted");
            }
            System.out.println(this + "terminating");
        }

        @Override
        public String toString() {
            return "TellerManager";
        }
    }

    static final int MAX_SIZE = 50;
    static final int ADJUSTMENT_PERIOD = 1000;
    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        CustomerLine customers = new CustomerLine(MAX_SIZE);
        exec.execute(new CustomerGenerator(customers));
        exec.execute(new TellerManager(exec, customers, ADJUSTMENT_PERIOD));
        if(args.length > 0){
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        }else{
            System.out.println("Press Enter to quit");
            System.in.read();
        }
        exec.shutdownNow();

    }

}
