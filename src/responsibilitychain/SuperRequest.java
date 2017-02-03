package responsibilitychain;

/**
 * Created by EricLi on 2016/11/14.
 * Email me : EricLi1235@gmail.com.
 */
public  class SuperRequest implements IRequest {

    protected Object object;

    public void addRequest(Object o){
        object = o;
    }

    public Object getRequest() {
        return object;
    }
}
