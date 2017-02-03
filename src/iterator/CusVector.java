package iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EricLi on 2016/11/18.
 * Email me : EricLi1235@gmail.com.
 */
public class CusVector<T> implements IAggregate<T> {

    private List<T> list = new ArrayList<>();

    @Override
    public void add(T data) {
        list.add(data);
    }

    @Override
    public void remove(T object) {
        list.remove(object);
    }

    @Override
    public void remove(int i) {
        list.remove(i);
    }

    @Override
    public Iterator<T> iterator() {
        //把数据源注入到迭代器里面
        return new ConcreteIterator<>(list);
    }
}
