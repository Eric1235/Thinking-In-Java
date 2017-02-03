package thread.threadpool;

/**
 * Created by EricLi on 2017/1/11.
 * Email me : EricLi1235@gmail.com.
 */
public class LiftOff implements Runnable{

    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LiftOff(){}

    public LiftOff(int countDown){
        this.countDown = countDown;
    }

    public String status(){
        return "#" + id + "(" + (countDown > 0 ? countDown : "Liffroff!\n") + "),";
    }
    @Override
    public void run() {

        while (countDown-- > 0){
            System.out.print(status());
            Thread.yield();//暂停当前线程，执行其他线程
        }
    }
}
