package genericity.genericarray;

import java.lang.reflect.Array;

/**
 * Created by EricLi on 2016/12/11.
 * Email me : EricLi1235@gmail.com.
 */
public class GenericArrayWithToken<T> {
    private T[] array;

    //传入Class，以便从擦除中恢复
    public GenericArrayWithToken(Class<T> type, int sz){
        array = (T[]) Array.newInstance(type, sz);
    }

    public void put(int index, T item){
        array[index] = item;
    }

    public T get(int index){
        return array[index];
    }

    public T[] rep(){
        return array;
    }

    public static void main(String[] args){
        GenericArrayWithToken<Integer> gai = new GenericArrayWithToken<>(Integer.class,10);
        Integer[] ai = gai.rep();//运行时能够得到正确的类型
        System.out.println(ai);
    }

}
