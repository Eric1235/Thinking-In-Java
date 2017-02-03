package responsibilitychain;

/**
 * Created by EricLi on 2016/11/15.
 * Email me : EricLi1235@gmail.com.
 */

/**
 * 现在我写的这种，是一种不纯的
 */
public class Client {
    public static void main(String[] args){
        GroupLeaderHandler groupLeader = new GroupLeaderHandler("GroupLeader",1000);
        SuperHandler superHandler = new GroupLeaderHandler("Manager",5000);
        SuperHandler bossHandler = new GroupLeaderHandler("Boss",10000);

        groupLeader.nextHandler = superHandler;
        superHandler.nextHandler = bossHandler;
        bossHandler.nextHandler = null;

        IRequest request = new SuperRequest();
        request.addRequest(new Integer(100));

        groupLeader.handleRequest(request);

        IRequest request1 = new SuperRequest();
        request1.addRequest(new Integer(1000));
        groupLeader.handleRequest(request1);

        IRequest request2 = new SuperRequest();
        request2.addRequest(new Integer(6000));
        groupLeader.handleRequest(request2);

        IRequest request3 = new SuperRequest();
        request3.addRequest(new Integer(1500));
        groupLeader.handleRequest(request3);

        IRequest request4 = new SuperRequest();
        request4.addRequest(new Integer(60000));
        groupLeader.handleRequest(request4);

    }
}
