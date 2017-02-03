package state;

/**
 * Created by lixinkun on 2016/11/10.
 */
public class TVOnState implements IState {
    public void turnUp() {
        System.out.println("调高音量");
    }

    public void turnDown() {
        System.out.println("调低音量");
    }

    public void nextChannel() {
        System.out.println("下一个频道");
    }

    public void prevChannel() {
        System.out.println("上一个频道");
    }
}
