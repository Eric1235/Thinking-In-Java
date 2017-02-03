package thinkinginjava;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by EricLi on 2016/11/22.
 * Email me : EricLi1235@gmail.com.
 */
public class Statistics {
    public static void main(String[] args){
        Random random = new Random(47);
        Map<Integer,Integer> m = new HashMap<>();
        int a[] = new int[20];
        for (int i = 0 ; i < 20 ; i++){
            a[i] = 0;
        }
        for(int i = 0 ; i < 10000; i ++){
            int r = random.nextInt(20);
            Integer req = m.get(r);
            a[r] ++;
            m.put(r,req == null? 1:req+1);//发生了自动打包和解包
        }
        System.out.println(m);

        for (int i = 0 ; i < 20 ; i++){
            System.out.println(i+"出现的次数为："+a[i]);
        }
    }
}
