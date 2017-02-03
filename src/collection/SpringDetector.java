package collection;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by EricLi on 2016/12/17.
 * Email me : EricLi1235@gmail.com.
 */
public class SpringDetector {
    public static <T extends Groundhog> void detectSpring(Class<T> type) throws Exception{
        Constructor<T> ghog = type.getConstructor(int.class);
        Map<Groundhog, Prediction> map = new HashMap<>();
        for(int i = 0 ; i < 10 ; i ++){
            map.put(ghog.newInstance(i), new Prediction());
        }
        System.out.println("map=" + map);
        Groundhog gh = ghog.newInstance(3);
        System.out.println("Looking for:" + gh);
        //不去覆盖hashCode()和equals(),使用散列的数据结构就无法正确处理你的键
        if(map.containsKey(gh)){
            System.out.println(map.get(gh));
        }else {
            /**
             * 目前这种状态，是一定会走到这里的，问题出在Groundhog自动地集成object，所以使用的是
             * Object的hashCode()生成散列码，而它默认是使用对象的地址计算散列码，因此，两者生成的散列码是不同的
             * 我们使用后者去进行判断，不能成功找到
             * 修改方法，要去覆盖hashCode（)和equals()方法
             */

            System.out.println("Key not found:" + gh);
        }
    }

    public static void main(String[] args)throws Exception{
        detectSpring(Groundhog.class);
    }
}
