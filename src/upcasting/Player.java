package upcasting;

/**
 * Created by lixinkun on 2016/11/9.
 */
public class Player {
    public void play(){
        System.out.print("B");
    }

    static void tune(Player player){
        player.play();
    }
}
