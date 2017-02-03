package state;

/**
 * Created by lixinkun on 2016/11/10.
 */
public class User {

    public static void main(String[] argv){
        TVController tvController = new TVController();
        tvController.turnOn();
        tvController.turnUp();
        tvController.turnDown();
        tvController.nextChannel();
        tvController.prevChannel();
        tvController.turnOff();
        tvController.turnUp();
        tvController.turnDown();
        tvController.nextChannel();
        tvController.prevChannel();
    }


}
