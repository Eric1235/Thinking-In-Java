package thread.checker;

/**
 * Created by EricLi on 2017/1/16.
 * Email me : EricLi1235@gmail.com.
 */
public abstract class IntGenerator {
    //保证可见性
    private volatile boolean canceled = false;
    public abstract int next();
    public void cancel(){
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
