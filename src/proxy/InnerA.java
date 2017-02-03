package proxy;

/**
 * Created by EricLi on 2016/12/6.
 * Email me : EricLi1235@gmail.com.
 */
public class InnerA {
    /**
     * 无论是内部类，还是匿名内部类，都无法阻止反射方法去调用
     */
    private static class C1 implements A{
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

    public static A makeA(){
        return new C1();
    }
}
