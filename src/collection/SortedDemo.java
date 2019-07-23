package collection;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by EricLi on 2016/12/15.
 * Email me : EricLi1235@gmail.com.
 * 按照元素的比较次序排序，不是按照插入次序
 * 如果要记住插入次序的，可以使用LinkedHashSet
 */
public class SortedDemo {
    public static void main(String[] args){
        //TreeSet是一种实现形式
        SortedSet<String> sortedSet = new TreeSet<>();
        Collections.addAll(sortedSet,"one two three four five six seven eight".split(" "));
        //结果已经经过排序
        print(sortedSet);
        String low = sortedSet.first();
        print(low);
        String high = sortedSet.last();
        print(high);
        Iterator<String> it =sortedSet.iterator();
        for(int i = 0 ; i <= 6 ; i ++){
            if(i == 3){
                low = it.next();
            }else if(i == 6){
                high = it.next();
            }else {
                it.next();
            }
        }
        print(low);
        print(high);
        print(sortedSet.subSet(low,high));
        //比high大的部分
        print(sortedSet.headSet(high));
        //比low小的部分
        print(sortedSet.tailSet(low));

    }

    /**
     *
     * @param o
     */
    private static void print(Object o){
        System.out.println(o);
    }
}
