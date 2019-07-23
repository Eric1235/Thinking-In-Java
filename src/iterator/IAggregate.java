package iterator;

/**
 * Created by EricLi on 2016/11/18.
 * Email me : EricLi1235@gmail.com.
 * 内部持有迭代器
 */
public interface IAggregate<T> {
    void add(T data);

    void remove(T object);

    void remove(int i);

    Iterator<T> iterator();
}
