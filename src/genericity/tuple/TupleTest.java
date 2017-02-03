package genericity.tuple;

/**
 * Created by EricLi on 2016/12/7.
 * Email me : EricLi1235@gmail.com.
 */
public class TupleTest {
    static TwoTuple<String, Integer> f(){
        return new TwoTuple<>("hi", 47);
    }

    static ThreeTuple<Amphibian,String,Integer> g(){
        return new ThreeTuple<>(new Amphibian(),"hi",47);
    }

    static FourTuple<Vehicle,Amphibian,String,Integer> h(){
        return new FourTuple<>(new Vehicle(),new Amphibian(),"hi",47);
    }

    static FiveTuple<Vehicle,Amphibian,String,Integer,Double> k(){
        return new FiveTuple<>(new Vehicle(),new Amphibian(),"hi",47,11.1);
    }

    public static void main(String[] args){
        TwoTuple<String,Integer> ttsi = f();
        System.out.println(ttsi);
//        ttsi.first = "there";//这样会导致编译错误，因为类型已经被指定为final

        System.out.println(g());
        System.out.println(h());
        System.out.println(k());

    }
}
