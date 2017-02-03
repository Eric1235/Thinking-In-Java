package command;

/**
 * Created by EricLi on 2016/11/16.
 * Email me : EricLi1235@gmail.com.
 */
public class Controller {
    private ICommand leftCommand;
    private ICommand rightCommand;
    private ICommand upCommand;
    private ICommand downCommand;

    public void setLeftCommand(ICommand command){
        this.leftCommand = command;
    }

    public void setRightCommand(ICommand command){
        this.rightCommand = command;
    }

    public void setUpCommand(ICommand command){
        this.upCommand = command;
    }

    public void setDownCommand(ICommand command){
        this.downCommand = command;
    }

    public void left(){
        leftCommand.execute();
    }

    public void right(){
        rightCommand.execute();
    }

    public void up(){
        upCommand.execute();
    }

    public void down(){
        downCommand.execute();
    }
}
