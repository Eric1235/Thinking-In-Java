package designpattern.observer;

/**
 * @author lixinkun
 * date: 2019-07-31 11:02
 */
public interface TaskLifeCycle<T> {

    void onStart(Thread thread);

    void onRunning(Thread thread);

    void onFinish(Thread thread, T result);

    void onError(Thread thread, Exception e);
}
