package genericity.erase_t;

/**
 * Created by EricLi on 2016/12/10.
 * Email me : EricLi1235@gmail.com.
 */
public class ClassTypeCapture<T> {
    Class<T> kind;

    public ClassTypeCapture(Class<T> kind) {
        this.kind = kind;
    }

    public boolean f(Object arg){
        return kind.isInstance(arg);//使用动态instanceof
    }

    public static void main(String[] args){
        ClassTypeCapture<Buliding> ctt1 = new ClassTypeCapture<>(Buliding.class);
        System.out.println(ctt1.f(new Buliding()));
        System.out.println(ctt1.f(new House()));

        ClassTypeCapture<House> ctt2 = new ClassTypeCapture<>(House.class);
        System.out.println(ctt2.f(new Buliding()));
        System.out.println(ctt2.f(new House()));
    }
}
