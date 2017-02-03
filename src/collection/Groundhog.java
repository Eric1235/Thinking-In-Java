package collection;

/**
 * Created by EricLi on 2016/12/17.
 * Email me : EricLi1235@gmail.com.
 */
public class Groundhog {
    protected int number;

    public Groundhog(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Groundhog #" + number ;
    }

//    //覆盖这个方法，计算的散列码就一样了
      //默认的方法是会使用默认的对象地址去计算散列码，导致每个对象的散列码都不同
//    @Override
//    public int hashCode() {
//        return number;
//    }
//
      //如果是同时满足的话，表达式是不是可以精简一下了呢
//    @Override
//    public boolean equals(Object obj) {
//        if(obj instanceof Groundhog){
//            Groundhog g = (Groundhog) obj;
//            if(g.number == number){
//                return true;
//            }else {
//                return false;
//            }
//        }else {
//            return false;
//        }
//    }
}
