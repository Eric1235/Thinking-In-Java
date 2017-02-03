package genericity.border;

import java.awt.*;

/**
 * Created by EricLi on 2016/12/11.
 * Email me : EricLi1235@gmail.com.
 */
public class Bounded extends Dimension implements HasColor, Weight {
    @Override
    public int weight() {
        return 0;
    }

    @Override
    public Color getColor() {
        return null;
    }
}
