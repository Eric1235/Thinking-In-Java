package instancetest;

/**
 * Created by EricLi on 2016/11/30.
 * Email me : EricLi1235@gmail.com.
 * instanceof 和 isInstance得到的结果是一致的
 * equals和==得到的结果是一致的
 * 但是，前一组保留了继承信息，可以感知到超类
 * 后一组丢失了继承信息，不能知道继承结构
 */
public class FamilyVsExactType {
    static void test(Object x){
        System.out.println("Testing x of type:" + x.getClass());
        System.out.println("x instanceof Base:" + (x instanceof  Base));
        System.out.println("x instanceof Derived:" + (x instanceof Derived));
        System.out.println("Base.isInstance(x):" + Base.class.isInstance(x));
        System.out.println("Derived.isInstance(x):" + Derived.class.isInstance(x));
        System.out.println("Base.class == x.getClass():" + (Base.class == x.getClass()));
        System.out.println("Derived.class == x.getClass():" +(Derived.class == x.getClass()));
        System.out.println("Derived.class.equals(x.getClass()):" + (Derived.class.equals(x.getClass())));
        System.out.println("Base.class.equals(x.getClass()):" +(Base.class.equals(x.getClass())));
    }
    public static void main(String[] args){
//        test(new Base());
        test(new Derived());
    }
}
