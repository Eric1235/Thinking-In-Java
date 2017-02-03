package upcasting;

/**
 * Created by EricLi on 2016/11/10.
 * Email me : EricLi1235@gmail.com.
 */
public class Glyph {
    void draw(){
        System.out.println("Glyph.draw()");
    }

    public Glyph(){
        System.out.println("Glyph() before draw()");
        draw();
        System.out.println("Glyph() after draw()");
    }
}
