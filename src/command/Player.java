package command;

/**
 * Created by EricLi on 2016/11/16.
 * Email me : EricLi1235@gmail.com.
 */
public class Player {

    public static void main(String[] args){
        Controller mController = new Controller();
        IGame game = new PushBox();
        ICommand leftCommand = new LeftCommand(game);
        ICommand rightCommand = new RightCommand(game);
        ICommand upCommand = new UpCommand(game);
        ICommand downCommand = new DownCommand(game);

        mController.setLeftCommand(leftCommand);
        mController.setRightCommand(rightCommand);
        mController.setUpCommand(upCommand);
        mController.setDownCommand(downCommand);

        mController.left();
        mController.right();
        mController.up();
        mController.down();
    }
}
