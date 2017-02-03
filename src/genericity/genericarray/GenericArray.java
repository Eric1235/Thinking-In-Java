package genericity.genericarray;

/**
 * Created by EricLi on 2016/12/10.
 * Email me : EricLi1235@gmail.com.
 * 没有东西能够转换Object[]
 */
public class GenericArray<T> {
    private T[] array;

    public GenericArray(int sz){
//        array = new T[sz];//错误的写法
        array = (T[]) new Object[sz];
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
        GenericArray<Integer> gai = new GenericArray<>(10);
        Object[] ia = gai.rep();
//        Integer[] a = gai.rep();//类型转换错误，会报错
    }
}
