package thread.interrupt;

/**
 * Created by EricLi on 2017/1/22.
 * Email me : EricLi1235@gmail.com.
 */
public class MultiLock {

    public synchronized void func1(int count){
        if(--count > 0){
            System.out.println("func1() calling func2() with count " + count);
            //这么做是有意义的，一个任务应该能够调用在同一个对象中的其他synchronized方法
            func2(count);
        }
    }

    public synchronized void func2(int count){
        if(--count > 0){
            System.out.println("func2() calling func1() with count " + count);
            func1(count);
        }
    }

    public static void main(String[] args){
        final MultiLock multiLock = new MultiLock();
        new Thread(){
            @Override
            public void run() {
                multiLock.func1(10);
            }
        }.start();
    }
}
