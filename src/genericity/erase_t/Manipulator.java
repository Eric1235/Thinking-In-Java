package genericity.erase_t;

/**
 * Created by EricLi on 2016/12/10.
 * Email me : EricLi1235@gmail.com.
 * 要是没有为T指定边界，是会发生编译期错误的
 * 使用关键字extends为T指定边界，然T是HasF导出的类型
 * T只是擦除到了HasF
 * 这样做的意义不是很大，可以直接用HasF代替T
 */
public class Manipulator<T extends HasF> {
    private T obj;
    public void manipulate(){
        obj.f();
    }

    public Manipulator(T x){
        obj = x;
    }
}
