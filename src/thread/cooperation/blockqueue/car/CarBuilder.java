package thread.cooperation.blockqueue.car;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by EricLi on 2017/2/20.
 * Email me : EricLi1235@gmail.com.
 */

/**
 * 接下来，就可以尝试去，添加更多的构造过程了
 */
public class CarBuilder {

    /**
     * 默认汽车实体类
     * 在本项目中，是不需要使用synchronized关键字进行同步的，但是如果去到其他的系统，就会造成崩溃
     */
    static class Car{
        private final int id;
        private boolean engine = false, driveTrain = false, wheels = false;

        public Car(int id) {
            this.id = id;
        }

        public Car(){
            id = -1;
        }

        public synchronized int getId() {
            return id;
        }

        public synchronized void addEngine(){
            engine = true;
        }

        public synchronized void addDriveTrain(){
            driveTrain = true;
        }

        public synchronized void addWheels(){
            wheels = true;
        }

        @Override
        public String toString() {
            return "Car " + id + "[ " + " engine " + engine +
                    " driveTrain " + driveTrain +
                    " wheels " + wheels + " ]";
         }
    }

    /**
     * 数据结构，对象沿着这里进行传递
     */
    static class CarQueue extends LinkedBlockingQueue<Car>{}

    /**
     * 底盘制造，只是汽车的一个框而已
     * 汽车生产的起点
     */
    static class ChassisBuilder implements Runnable{
        //car从这个数据结构开始流动
        private CarQueue carQueue;
        private int counter = 0 ;

        public ChassisBuilder(CarQueue carQueue) {
            this.carQueue = carQueue;
        }

        @Override
        public void run() {
            try {
                while(!Thread.interrupted()){
                    TimeUnit.MILLISECONDS.sleep(500);
                    Car c = new Car(counter++);
                    System.out.println("ChassisBuilder created " + c);
                    carQueue.put(c);
                }
            }catch (InterruptedException e){
                System.out.println("ChassisBuilder interrupted");
            }
            System.out.println("ChassisBuilder off");
        }
    }

    /**
     * 组装者
     * 对汽车的其他部件开始进行组装
     * 传递到另外一条数据结构
     */
    static class Assembler implements Runnable{
        //底盘生产线和完成产品生产线
        private CarQueue chassisQueue, finishingQueue;
        private Car car;
        //栅栏，为什么是4？看下去就明白度的了。3个步骤完成组装嘛，彼此等待，一起向前推进
        private CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        //机器人池
        private RobotPool robotPool;

        public Assembler(CarQueue chassisQueue, CarQueue finishingQueue, RobotPool robotPool) {
            this.chassisQueue = chassisQueue;
            this.finishingQueue = finishingQueue;
            this.robotPool = robotPool;
        }

        public Car car(){
            return car;
        }

        public CyclicBarrier barrier(){
            return cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    //得到车的空壳，然后开始进行零件组装
                    car = chassisQueue.take();
                    robotPool.hire(EngineRobot.class, this);
                    robotPool.hire(DriveTrainRobot.class, this);
                    robotPool.hire(WheelRobot.class, this);
                    cyclicBarrier.await();
                    //组装已经完成了，放到完成队列
                    finishingQueue.put(car);
                }
            }catch (InterruptedException e){
                System.out.println("Exiting Assembler via interrupted");
            }catch (BrokenBarrierException e){
                throw new RuntimeException(e);
            }
            System.out.println("Assembler off");
        }
    }

    /**
     * 最后的消费者
     * 报告车子已经组装完毕
     */
    static class Reporter implements Runnable{

        private CarQueue carQueue;

        public Reporter(CarQueue carQueue) {
            this.carQueue = carQueue;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    //打印成品车子
                    System.out.println(carQueue.take());
                }
            }catch (InterruptedException e){
                System.out.println("Exiting Reporter via interrupt");
            }
            System.out.println("Reporter off");
        }
    }

    /**
     * 机器人模板
     */
    static abstract class Robot implements Runnable{
        private RobotPool pool;

        public Robot(RobotPool pool) {
            this.pool = pool;
        }

        protected Assembler assembler;

        public Robot assignAssembler(Assembler assembler){
            this.assembler = assembler;
            return this;
        }

        private boolean engage = false;

        //开工，唤醒机器人
        public synchronized void engage(){
            engage = true;
            notify();
        }

        //主要执行功能，需要子类去进行实现
        abstract protected void performService();

        //处理流程
        @Override
        public void run() {
            try {
                powerDown();
                while (!Thread.interrupted()){
                    performService();
                    assembler.barrier().await();
                    powerDown();
                }
            }catch (InterruptedException e){
                System.out.println("Exiting " + this + " via interrupt");
            }catch (BrokenBarrierException e){
                throw new RuntimeException(e);
            }
            System.out.println(this + " off");
        }

        //关机
        private synchronized void powerDown()throws InterruptedException{
            engage = false;
            assembler = null;
            pool.release(this);
            while (engage == false){
                wait();
            }
        }

        @Override
        public String toString() {
            return getClass().getSimpleName()+ " ";
        }
    }

    static class EngineRobot extends Robot{

        public EngineRobot(RobotPool pool) {
            super(pool);
        }

        @Override
        protected void performService() {
            System.out.println(this + "installing engine");
            assembler.car().addEngine();
        }
    }

    static class DriveTrainRobot extends Robot{
        public DriveTrainRobot(RobotPool pool) {
            super(pool);
        }

        @Override
        protected void performService() {
            System.out.println(this + " installing DriveTrain");
            assembler.car().addDriveTrain();
        }
    }

    static class WheelRobot extends Robot{
        public WheelRobot(RobotPool pool) {
            super(pool);
        }

        @Override
        protected void performService() {
            System.out.println(this + " installing Wheels");
            assembler.car().addWheels();
        }
    }

    /**
     * 机器人池
     */
    static class RobotPool{
        private Set<Robot> pool = new HashSet<>();
        public synchronized void add(Robot r){
            pool.add(r);
            notifyAll();
        }

        //从机器人池中拿到机器人，然后开工
        public synchronized void hire(Class <? extends Robot> robotType, Assembler a) throws InterruptedException{
            for (Robot r : pool){
                if(r.getClass().equals(robotType)){
                    pool.remove(r);
                    r.assignAssembler(a);
                    r.engage();
                    return;
                }
            }
            wait();
            hire(robotType, a);
        }

        //把机器人放回到池子中，大概是休息池吧
        public synchronized void release(Robot r){
            add(r);
        }
    }

    //程序入口
    public static void main(String[] args)throws Exception{
        CarQueue chassisQueue= new CarQueue(),
                finishingQueue = new CarQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        RobotPool robotPool = new RobotPool();
        exec.execute(new EngineRobot(robotPool));
        exec.execute(new DriveTrainRobot(robotPool));
        exec.execute(new WheelRobot(robotPool));
        exec.execute(new Assembler(chassisQueue, finishingQueue, robotPool));
        exec.execute(new Reporter(finishingQueue));
        exec.execute(new ChassisBuilder(chassisQueue));
        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();
    }

}
