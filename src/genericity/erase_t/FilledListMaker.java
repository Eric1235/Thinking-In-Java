package genericity.erase_t;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EricLi on 2016/12/10.
 * Email me : EricLi1235@gmail.com.
 * 容器和数组的就不一样了
 */
public class FilledListMaker<T> {
    List<T> create(T t, int n){
        List<T> result = new ArrayList<T>();
        for(int i = 0 ; i < n ; i ++){
            result.add(t);
        }
        return result;
    }

    public static void main(String[] args){
        FilledListMaker<String> maker = new FilledListMaker<>();
        List<String> list = maker.create("Hi", 4);
        System.out.println(list);
    }
}
