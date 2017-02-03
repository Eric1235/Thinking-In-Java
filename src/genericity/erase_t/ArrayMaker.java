package genericity.erase_t;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by EricLi on 2016/12/10.
 * Email me : EricLi1235@gmail.com.
 */
public class ArrayMaker<T> {
    private Class<T> kind;
    public ArrayMaker(Class<T> kind){
        this.kind = kind;
    }

    @SuppressWarnings("unchecked")
    T[] create(int size){
        return (T[]) Array.newInstance(kind,size);//并不会创建任何含类型信息的东西，但是Array.newInstance()是一种推荐的方式
    }

    List<T> create(){
        return new ArrayList<T>();
    }

    public static void main(String[] args){
        ArrayMaker<String> stringMaker = new ArrayMaker<>(String.class);
        String[] strings = stringMaker.create(9);
        List<String> list = stringMaker.create();
        System.out.println(Arrays.toString(strings));
        System.out.println(list);
    }
}
