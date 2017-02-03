package command;

/**
 * Created by EricLi on 2016/11/16.
 * Email me : EricLi1235@gmail.com.
 * 推箱子游戏
 */
public class PushBox implements IGame{
    public void left() {
        System.out.println("小人向左");
    }

    public void right() {
        System.out.println("小人向右");
    }

    public void up() {
        System.out.println("小人向上");
    }

    public void down() {
        System.out.println("小人向下");
    }
}
