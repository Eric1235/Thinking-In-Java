package responsibilitychain;

/**
 * Created by EricLi on 2016/11/14.
 * Email me : EricLi1235@gmail.com.
 */
public abstract class SuperHandler implements IHandler {

    protected SuperHandler nextHandler;

    private int data;

    protected String name;

    public int limit() {
        return data;
    }

    public SuperHandler(String name, int data){
        this.name = name;
        this.data = data;
    }

    public void handleRequest(IRequest request) {
        Object o = request.getRequest();
        if(o instanceof Integer){
            int price = ((Integer) o).intValue();
            if(price <= limit()){
                handle(price);
            }else {
                if(nextHandler != null){
                    nextHandler.handleRequest(request);
                }else {
                    System.out.println(price+"元的情况太过严重，没有人可以处理");
                }
            }
        }
    }

    //具体处理的方法也是同样抽象出来
    public abstract void handle(int value);
}
