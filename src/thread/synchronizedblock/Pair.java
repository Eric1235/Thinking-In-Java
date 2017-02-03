package thread.synchronizedblock;

/**
 * Created by EricLi on 2017/1/18.
 * Email me : EricLi1235@gmail.com.
 */
public class Pair {
    private int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair(){
        this(0, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incrementX(){
        //线程不安全啊
        x++;
    }

    public void incrementY(){
        y++;
    }

    public class PairValuesNotEqualException extends RuntimeException{
        public PairValuesNotEqualException() {
            super("Pair values not equal: " + Pair.this);
        }
    }

    public void checkState(){
        if(x != y){
            throw new PairValuesNotEqualException();
        }
    }

    @Override
    public String toString() {
        return "Pair.x = " + x + " Pair.y" + y;
    }
}
