package genericity.border;

/**
 * Created by EricLi on 2016/12/11.
 * Email me : EricLi1235@gmail.com.
 */
public class HoldItem<T> {
    T item;

    public HoldItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}
