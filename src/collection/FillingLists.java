package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

/**
 * Created by EricLi on 2016/12/11.
 * Email me : EricLi1235@gmail.com.
 * 两种填充容器的方法
 */
public class FillingLists {
    public static void main(String[] args){
        List<StringAddress> list = new ArrayList<>(Collections.nCopies(4,new StringAddress("China")));
        System.out.println(list);
        Collections.fill(list, new StringAddress("Hi"));
        System.out.println(list);
    }

}
