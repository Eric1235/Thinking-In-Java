package command;

/**
 * Created by EricLi on 2016/11/16.
 * Email me : EricLi1235@gmail.com.
 */
public class LeftCommand implements ICommand{

    private IGame game;

    public LeftCommand(IGame game){
        this.game = game;
    }

    public void execute() {
        game.left();
    }
}
