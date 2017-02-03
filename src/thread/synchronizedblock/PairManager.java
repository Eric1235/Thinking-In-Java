package thread.synchronizedblock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by EricLi on 2017/1/18.
 * Email me : EricLi1235@gmail.com.
 */

/**
 * 模板方法
 */
public abstract class PairManager {
    AtomicInteger checkCounter = new AtomicInteger(0);
    protected Pair pair = new Pair();
    private List<Pair> storage = Collections.synchronizedList(new ArrayList<>());

    public synchronized Pair getPair(){
        return new Pair(pair.getX(), pair.getY());
    }

    protected void store(Pair p){
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
            if(storage.size() > 50){
                System.out.println("full");
                for (int i = 0 ; i < storage.size() ; i ++){
                    System.out.println(storage.get(i));
                }
                System.exit(0);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public abstract void increment();
}
