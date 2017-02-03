package collection;

/**
 * Created by EricLi on 2016/12/11.
 * Email me : EricLi1235@gmail.com.
 */
public class StringAddress {
    private String s;

    public StringAddress(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "StringAddress{" +
                "s='" + s + '\'' +
                '}';
    }
}
