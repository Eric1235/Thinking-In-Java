package annotation;

/**
 * Created by EricLi on 2017/1/9.
 * Email me : EricLi1235@gmail.com.
 */
public class Testable {
    public void execute(){
        System.out.println("Executing...");
    }

    @Test void testExecute() {execute();}
}
