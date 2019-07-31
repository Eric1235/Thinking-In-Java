package designpattern.observer;

/**
 * @author lixinkun
 * date: 2019-07-31 11:06
 */
public class ObservableThread<T> extends Thread implements Observable{

    private final TaskLifeCycle<T> lifeCycle;

    private final Task<T> task;

    private Cycle cycle;


    public ObservableThread(TaskLifeCycle<T> lifeCycle, Task<T> task) {
        super();
        if (task == null) {
            throw new Error();
        }
        this.lifeCycle = lifeCycle;
        this.task = task;
    }

    public ObservableThread(Task<T> task) {
        this(new EmptyLifeCycle<>(), task);
    }

    @Override
    public Cycle getCycle() {
        return cycle;
    }

    @Override
    public void run() {
        this.update(Cycle.STARTED, null, null);
        try {
            this.update(Cycle.RUNNING, null, null);
            T result = task.call();
            this.update(Cycle.DONE, result, null);
        } catch (Exception e) {
            this.update(Cycle.ERROR, null, e);
        }
    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (lifeCycle == null) {
            return;
        }
        try {
            switch (cycle) {
                case DONE:
                    this.lifeCycle.onFinish(currentThread(), result);
                    break;
                case STARTED:
                    this.lifeCycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifeCycle.onRunning(currentThread());
                    break;
                case ERROR:
                    this.lifeCycle.onError(currentThread(), e);
                    break;
            }
        } catch (Exception e1) {
            if (cycle == Cycle.ERROR) {
                throw e1;
            }
        }
    }
}
