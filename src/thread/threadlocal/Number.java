package thread.threadlocal;

/**
 * @author lixinkun
 * date: 2019-07-23 15:33
 */
public class Number {
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Number [num=" + num + "]";
    }
}
