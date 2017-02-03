package collection;

import java.util.Random;

/**
 * Created by EricLi on 2016/12/17.
 * Email me : EricLi1235@gmail.com.
 */
public class Prediction {
    private static Random rand = new Random(47);
    private boolean shadow = rand.nextDouble() > 0.5;

    @Override
    public String toString() {
        if(shadow){
            return "Six more weeks of Winter!";
        }else {
            return "Early Spring!";
        }
    }
}
