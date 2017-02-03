package genericity.tuple;

import static genericity.tuple.Tuple.tuple;//使用静态导入

/**
 * Created by EricLi on 2016/12/10.
 * Email me : EricLi1235@gmail.com.
 */
public class TupleTest2 {
    //具体参数化
    static TwoTuple<String,Integer> f(){
        return tuple("hi", 47);
    }

    //非参数化
    static TwoTuple f2(){
        return tuple("Hi", 47);
    }

    public static void main(String[] args){
        TwoTuple<String, Integer> f2Result = f2();
        System.out.println(f());
        System.out.println(f2());
    }

}
