package thread.daemon;

import java.util.concurrent.ThreadFactory;

/**
 * Created by EricLi on 2017/1/12.
 * Email me : EricLi1235@gmail.com.
 */

/**
 * 咱们去定制线程，定制好的线程，给线程池使用
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return null;
    }
}
