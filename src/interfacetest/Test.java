package interfacetest;

/**
 * Created by EricLi on 2016/11/16.
 * Email me : EricLi1235@gmail.com.
 * C和D都扩展了，Test实现C和D的时候，是不会有两个A出现的
 */
public class Test implements C,D {

    //只有一个A
    public int A(int data) {
        return data + 3;
    }

    public int C(int data) {
        return data -3;
    }

    public int D(int data) {
        return data;
    }
}
