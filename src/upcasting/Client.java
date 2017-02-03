package upcasting;

/**
 * Created by EricLi on 2016/11/10.
 * Email me : EricLi1235@gmail.com.
 */
public class Client {
    public static void main(String[] args){
        RoundGlyph roundGlyph = new RoundGlyph(5);

        roundGlyph.draw();//要自己亲自去进行调用的时候，才会起作用
    }
}
