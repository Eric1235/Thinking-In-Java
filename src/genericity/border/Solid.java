package genericity.border;

/**
 * Created by EricLi on 2016/12/11.
 * Email me : EricLi1235@gmail.com.
 */
public class Solid<T extends  Dimension & HasColor & Weight> {
    T item;
    Solid(T item){
        this.item = item;
    }

    T getItem(){
        return item;
    }

    java.awt.Color color(){
        return item.getColor();
    }

    int getX(){
        return item.x;
    }

    int getY(){
        return item.y;
    }

    int getZ(){
        return item.z;
    }

    int getWeight(){
        return item.weight();
    }
}
