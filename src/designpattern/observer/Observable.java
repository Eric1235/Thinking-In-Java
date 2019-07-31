package designpattern.observer;

/**
 * @author lixinkun
 * date: 2019-07-31 10:57
 */
public interface Observable {

    enum Cycle{
        STARTED, RUNNING,DONE,ERROR
    }

    Cycle getCycle();

    void start();

    void interrupt();
}
