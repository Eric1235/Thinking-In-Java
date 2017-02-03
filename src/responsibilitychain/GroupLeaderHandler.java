package responsibilitychain;

/**
 * Created by EricLi on 2016/11/15.
 * Email me : EricLi1235@gmail.com.
 */
public class GroupLeaderHandler extends SuperHandler {

    public GroupLeaderHandler(String name, int price){
        super(name,price);
    }

    public void handle(int value) {
        System.out.println(name + "可以报销" + value + "元的费用");
    }

}
