package reflecttest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EricLi on 2016/11/29.
 * Email me : EricLi1235@gmail.com.
 * 泛型编程
 */
public class FilledList<T> {
    private Class<T> type;
    public FilledList(Class<T> type){
        this.type = type;
    }

    public List<T> create(int nElements){
        List<T> result = new ArrayList<>();
        try{
            for(int i = 0 ; i < nElements ; i ++){
                result.add(type.newInstance());//累加一次
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static void main(String[] args){
        FilledList<CountedInteger> filledList = new FilledList<>(CountedInteger.class);
        System.out.println(filledList.create(15));
    }
}
