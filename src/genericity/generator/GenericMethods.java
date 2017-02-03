package genericity.generator;

/**
 * Created by EricLi on 2016/12/8.
 * Email me : EricLi1235@gmail.com.
 */
public class GenericMethods {
    public <T> T f(T x){
        System.out.println(x.getClass().getCanonicalName());
        return x;
    }

    public static void main(String[] args){
        GenericMethods gm = new GenericMethods();
        String s ="";
        gm.f(s);
        gm.f(1);
        gm.f(1.0);
        gm.f(1.0F);
        gm.f(gm);
    }
}
