package designpattern.observer;

import java.util.concurrent.TimeUnit;

/**
 * @author lixinkun
 * date: 2019-07-31 11:16
 */
public class Test {

    public static void main(String[] args) {
        final TaskLifeCycle<String> lifeCycle = new EmptyLifeCycle<String>();
        Observable observable = new ObservableThread<>(lifeCycle,()-> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("finish");
            return "Hello";
        });
        observable.start();
    }
}
