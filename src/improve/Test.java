package improve;

/**
 * Created by EricLi on 2018/1/10.
 * Email me : EricLi1235@gmail.com.
 */
public class Test {
    public synchronized void method (Object o) {

    }

    public void method1 (Object o) {

    }

    /**
     * 不能在for循环里面去调用同步方法，很耗时
     */
    private void test() {
        for (int i = 0 ; i < 10000 ; i ++) {
            method(1);
        }
    }

    private void test1() {
//        synchronized {
//            for(int i = 0 ; i < 1000 ; i ++) {
//                method1(1);
//            }
//        }
    }
}
