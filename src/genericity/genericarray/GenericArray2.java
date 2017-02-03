package genericity.genericarray;

/**
 * Created by EricLi on 2016/12/11.
 * Email me : EricLi1235@gmail.com.
 */
public class GenericArray2<T> {
    private Object[] array;
    public GenericArray2(int sz){
        array = new Object[sz];
    }

    public void put(int index, T item){
        array[index] = item;
    }

    public T get(int index){
        return (T) array[index];
    }

    public T[] rep(){
        return (T[]) array;
    }

    public static void main(String[] args){
        GenericArray2<Integer> gai = new GenericArray2<>(10);
        for(int i = 0 ; i < 10 ; i ++){
            gai.put(i,i);
        }

        for(int i = 0 ; i < 10 ; i ++){
            System.out.println(gai.get(i));
        }

        try{
            Integer[] ai = gai.rep();//这样子依旧是不能转型
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
