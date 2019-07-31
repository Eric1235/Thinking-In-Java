package designpattern.observer;

/**
 * 这里添加感兴趣的执行事件
 * @author lixinkun
 * date: 2019-07-31 11:04
 */
public class EmptyLifeCycle<T> implements TaskLifeCycle<T> {
    @Override
    public void onStart(Thread thread) {
        System.out.println("thread start");
    }

    @Override
    public void onRunning(Thread thread) {
        System.out.println("thread running");
    }

    @Override
    public void onFinish(Thread thread, T result) {
        System.out.println("thread finish result:" + result);
    }

    @Override
    public void onError(Thread thread, Exception e) {
        System.out.println("thread error");
    }
}
