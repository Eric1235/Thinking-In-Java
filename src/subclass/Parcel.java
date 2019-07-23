package subclass;

/**
 * Created by EricLi on 2018/3/4.
 * Email me : EricLi1235@gmail.com.
 */
public class Parcel {
    //匿名内部类，直接就实现了这个接口，但是没有名字
    public Contants contants() {
        return new Contants() {
            private int i = 11;
            public int value() {
                return i;
            }
        };
    }

    //嵌套类，和外围类没有联系
    protected static class Destination {

    }
}
