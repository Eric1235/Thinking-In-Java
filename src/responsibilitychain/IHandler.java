package responsibilitychain;

/**
 * Created by EricLi on 2016/11/14.
 * Email me : EricLi1235@gmail.com.
 */
public interface IHandler {
    int limit();
    void handleRequest(IRequest request);
}
