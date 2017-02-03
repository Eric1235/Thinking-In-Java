package state;

/**
 * Created by lixinkun on 2016/11/10.
 */
public class TVOffState implements IState{
    public void turnUp() {
        System.out.println("都关机了，增加音量不起作用");
    }

    public void turnDown() {
        System.out.println("都关机了，减少音量不起作用");
    }

    public void nextChannel() {
        System.out.println("都关机了，下一频道不起作用");
    }

    public void prevChannel() {
        System.out.println("都关机了，上一频道不起作用");
    }
}
