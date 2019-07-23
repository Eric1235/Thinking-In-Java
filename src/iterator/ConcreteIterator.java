package iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EricLi on 2016/11/18.
 * Email me : EricLi1235@gmail.com.
 */
public class ConcreteIterator<T> implements Iterator<T> {

    private List<T> list = new ArrayList<>();

    //一个游标位置
    private int cursor = 0;

    public ConcreteIterator(List<T> list){
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return cursor != list.size();
    }

    @Override
    public T next() {
        T o = null;
        if(hasNext()){
            o = list.get(cursor ++);
        }
        return o;
    }
}
