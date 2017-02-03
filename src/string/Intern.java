package string;

/**
 * Created by EricLi on 2017/1/17.
 * Email me : EricLi1235@gmail.com.
 */

import java.util.Random;

/**
 * 使用intern就是为了节省空间
 * new创建的字符串不进入字符串池
 * 常量池就类似一个JAVA系统级别提供的缓存
 * perm区只有4m，如果大量使用intern(),会造成oom的
 * 直接使用双引号声明出来的String对象会直接存储在常量池中。
 * 如果不是用双引号声明的String对象，可以使用String提供的intern方法。
 * intern 方法会从字符串常量池中查询当前字符串是否存在，若不存在就会将当前字符串放入常量池中
 */
public class Intern {

    static final int MAX = 1000 * 10000;
    static final String[] arr = new String[MAX];

    public static void main(String[] args)throws Exception{

        String s3 = new String("1") + new String("1");//有两个匿名对象
        s3.intern();
        String s4 = "11";//首先执行String s4 = "11";声明 s4 的时候常量池中是不存在“11”对象的
//        s3.intern();//放在这里去实现的话，就不行了哦
        System.out.println(s3 == s4);

        String s6 = "a";
        String s7 = "b";
        String s8 = "ab";
        String s9 = new String("ab");
        String s10 = new String("a") + new String("b");
        String s11 = "a" + "b";

        System.out.println(s8 == s9);
        System.out.println(s8 == s9.intern());
        System.out.println(s8 == s11);
        System.out.println(s9 == s10);
        System.out.println(s9.intern() == s10.intern());
        System.out.println(s9.intern() == s11);

        testIntern(args);
    }

    public static void testIntern(String[] args) throws Exception{
        Integer[] DB_DATA = new Integer[10];
        Random random = new Random(10 * 10000);
        for (int i = 0 ; i < DB_DATA.length ; i ++){
            DB_DATA[i] = random.nextInt();
        }
        long t = System.currentTimeMillis();
        for (int i = 0 ; i < MAX ; i ++){
//            arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length]));
            arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length])).intern();//减少对象的生成
        }
        System.out.println((System.currentTimeMillis() - t) + "ms");
        System.gc();
    }
}
