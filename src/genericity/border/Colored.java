package genericity.border;

/**
 * Created by EricLi on 2016/12/11.
 * Email me : EricLi1235@gmail.com.
 */
public class Colored<T extends HasColor> {
    T item;
    Colored(T item){
        this.item = item;
    }
    T getItem(){
        return item;
    }

    java.awt.Color color(){
        return item.getColor();
    }
}
