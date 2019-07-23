package improve;

import java.util.Date;

/**
 * for循环需要定义一个循环变量来遍历每一个需要循环的对象，所以，循环次数多的要放到内侧
 * 外大内小
 *
 * Created by EricLi on 2018/1/10.
 * Email me : EricLi1235@gmail.com.
 */
public class TestFor {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int count = 0;
        for (int i = 0 ; i < 100 ; i ++) {
            for (int j = 0 ; j < 1000000 ; j ++) {
                count ++;
                new Date();
            }
        }
        System.out.println(count);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        count = 0 ;
        for (int i =  0 ; i < 1000000 ; i ++) {
            for (int j = 0 ; j < 100 ; j ++) {
                count ++;
                new Date();
            }
        }

        System.out.println(count);
        System.out.println(System.currentTimeMillis() - start);
    }
}
