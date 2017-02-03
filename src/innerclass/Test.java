package innerclass;

/**
 * Created by EricLi on 2016/11/18.
 * Email me : EricLi1235@gmail.com.
 */
public class Test {

    private int t1;

    public Test(int t1) {
        this.t1 = t1;
    }

    class Counter implements ICounter{
        public int sum(int[] a) {
            int sum = 0;
            for(int i = 0 ; i < a.length; i ++){
                sum += a[i];
            }
            return sum;
        }

        public int sumTest(int i){
            return t1 + i;
        }
    }

    class Max implements IMax{
        public Object max(Object x, Object y) {
            if( x instanceof Integer){

            }
            return new Integer(1);
        }
    }

    public Counter count(){
        return new Counter();
    }

    public Max max(){
        return new Max();
    }
}
