package command;

/**
 * Created by EricLi on 2016/11/16.
 * Email me : EricLi1235@gmail.com.
 */
public class DownCommand implements ICommand {
    private IGame game;
    public void execute() {
        game.down();
    }

    public DownCommand(IGame game) {
        this.game = game;
    }
}
