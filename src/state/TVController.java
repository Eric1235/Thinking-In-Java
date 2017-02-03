package state;

/**
 * Created by lixinkun on 2016/11/10.
 */
public class TVController implements IPowerController {

    private IState state = new TVOffState();

    public void turnOn() {
        setState(new TVOnState());
        System.out.println("TV ON");
    }

    public void turnOff() {
        setState(new TVOffState());
        System.out.println("TV OFF");
    }

    private void setState(IState state){
        this.state = state;
    }

    //要有和状态的同名接口？一样操作的接口？？？？
    public void turnUp() {
        state.turnUp();
    }

    public void turnDown() {
        state.turnDown();
    }

    public void nextChannel() {
        state.nextChannel();
    }

    public void prevChannel() {
        state.prevChannel();
    }
}
