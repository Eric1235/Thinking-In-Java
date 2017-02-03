package thread.synchronizedblock;

/**
 * Created by EricLi on 2017/1/18.
 * Email me : EricLi1235@gmail.com.
 */
public class PairManager1 extends PairManager {
    //继承的时候，是可以去添加关键字的，不属于特征签名的组成部分
    @Override
    public synchronized void increment() {
        pair.incrementX();
        pair.incrementY();
        store(getPair());//要使用新对象这种方式去存
    }
}
