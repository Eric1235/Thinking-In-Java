package proxy;

/**
 * Created by EricLi on 2016/12/6.
 * Email me : EricLi1235@gmail.com.
 */
public class C implements A {
    @Override
    public void a() {
        System.out.println("public C.a()");
    }

    public void b(){
        System.out.println("public C.b()");
    }

    void u(){
        System.out.println("public C.u()");
    }

    protected void v(){
        System.out.println("public C.v()");
    }

    protected void w(){
        System.out.println("public C.w()");
    }
}
