package thread;

/**
 * Created by EricLi on 2017/1/13.
 * Email me : EricLi1235@gmail.com.
 */
//实现runnable的好处，就是可以继承自其它的类，而继承Thread则不可
public class SelfManaged implements Runnable {
    private int countDown = 5;
    private Thread t = new Thread(this);

    public SelfManaged() {
        //这样会变得很有问题，因为任务可能在构造器还没有完成的时候就开始执行，造成任务的不稳定
        t.start();//在构造器中直接启动线程，还是，用线程池吧
        //要是我下面还有代码呢。。。
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + "("+ countDown +")";
    }

    @Override
    public void run() {
        while (true){
            System.out.println(this);
            if(--countDown == 0){
                return;
            }
        }
    }

    public static void main(String[] args){
        for(int i = 0 ; i < 5 ; i ++){
            new SelfManaged();
        }
    }
}
