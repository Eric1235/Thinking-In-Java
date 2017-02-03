package proxy;

import java.util.List;

/**
 * Created by EricLi on 2016/12/6.
 * Email me : EricLi1235@gmail.com.
 */
public interface Robot {
    String name();
    String model();
    List<Operation> operations();
    class Test{

        public static void test(Robot r){
            if(r == null){
                System.out.println("robot null");
            }
        }
    }
}
