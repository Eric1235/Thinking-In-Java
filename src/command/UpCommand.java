package command;

/**
 * Created by EricLi on 2016/11/16.
 * Email me : EricLi1235@gmail.com.
 */
public class UpCommand implements ICommand {
    private IGame game;
    public void execute() {
        game.up();
    }

    public UpCommand(IGame game) {
        this.game = game;
    }
}
