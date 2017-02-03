package genericity.border;

/**
 * Created by EricLi on 2016/12/11.
 * Email me : EricLi1235@gmail.com.
 * 更优雅的方式，就是去使用继承
 * 弄清楚上界和下界好了
 */
public class ColoredDimension<T extends Dimension & HasColor> {
    T item;
    ColoredDimension(T item){
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
}
