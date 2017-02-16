package thread.cooperation.objectpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by EricLi on 2017/2/14.
 * Email me : EricLi1235@gmail.com.
 */

/**
 * 连接池，对对象进行管理
 * @param <T>
 */
public class Pool<T> {
    private int size;
    private List<T> items = new ArrayList<>();
    private volatile boolean[] checkedOut;//跟踪对象
    private Semaphore available;

    //初始化对象池
    public Pool(Class <T> classObject, int size) {
        this.size = size;
        checkedOut = new boolean[size];
        available = new Semaphore(size, true);
        for (int i = 0 ; i < size; i ++){
            try {
                items.add(classObject.newInstance());
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    public T checkOut() throws InterruptedException{
        available.acquire();
        return getItem();
    }

    public void checkIn(T x){
        if (releaseItem(x)){
            available.release();
        }
    }

    private synchronized T getItem(){
        for (int i = 0 ; i < size; i ++){
            if (! checkedOut[i]){
                checkedOut[i] = true;
                return items.get(i);
            }
        }
        return null;
    }

    private boolean releaseItem(T item){
        int index = items.indexOf(item);
        if(index == -1){
            return false;
        }
        if(checkedOut[index]){
            checkedOut[index] = false;
            return true;
        }
        return false;
    }
}
