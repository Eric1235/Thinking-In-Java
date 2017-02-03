package command;

/**
 * Created by EricLi on 2016/11/16.
 * Email me : EricLi1235@gmail.com.
 */
public class RightCommand implements ICommand{
    private IGame game;
    public RightCommand(IGame game) {
        this.game = game;
    }

    public void execute() {
        game.right();
    }
}
