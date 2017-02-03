package genericity.tuple;

import java.util.ArrayList;

/**
 * Created by EricLi on 2016/12/10.
 * Email me : EricLi1235@gmail.com.
 */
public class TupleList<A,B,C,D> extends ArrayList<FourTuple<A,B,C,D>> {
    public static void main(String[] args){
        TupleList<Vehicle,Amphibian,String,Integer> list = new TupleList<>();
        list.add(TupleTest.h());
        list.add(TupleTest.h());
        for(FourTuple<Vehicle,Amphibian,String,Integer> l : list){
            System.out.println(l);
        }
    }
}
