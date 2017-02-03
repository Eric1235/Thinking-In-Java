package upcasting;

/**
 * Created by EricLi on 2016/11/10.
 * Email me : EricLi1235@gmail.com.
 */
public class RoundGlyph extends Glyph {
    private int radius = 1;

    RoundGlyph(int r){
        radius = r;
        System.out.println("RoundGlyph() ,radius = " + radius);
    }

    void draw(){
        System.out.println("RoundGlyph draw(), radius=" + radius);
    }
}
