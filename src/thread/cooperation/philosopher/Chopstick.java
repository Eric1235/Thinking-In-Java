package thread.cooperation.philosopher;

/**
 * Created by EricLi on 2017/2/5.
 * Email me : EricLi1235@gmail.com.
 */

public class Chopstick {
    private boolean taken = false;

    public synchronized void take()throws InterruptedException{
        while (taken){
            wait();
        }
        taken = true;
    }

    public synchronized void drop(){
        taken = false;
        notifyAll();
    }
}
