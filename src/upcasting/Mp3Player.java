package upcasting;

/**
 * Created by lixinkun on 2016/11/9.
 */
public class Mp3Player extends Player {
    public static void main(String[] args){
        Mp3Player player = new Mp3Player();
        Player.tune(player);//向上转型
    }


}
