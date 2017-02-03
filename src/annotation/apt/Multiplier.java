package annotation.apt;

/**
 * Created by EricLi on 2017/1/10.
 * Email me : EricLi1235@gmail.com.
 */
@ExtractInterface("IMultiplier")
public class Multiplier {
    public int multiply(int x, int y){
        int total = 0;
        for (int i = 0 ; i < x ; i ++){
            total = add(total, y);
        }
        return total;
    }

    private int add(int x, int y){
        return x + y;
    }

    public static void main(String[] args){
        Multiplier m = new Multiplier();
        System.out.println("11 * 16 = " + m.multiply(11, 16));
    }
}
