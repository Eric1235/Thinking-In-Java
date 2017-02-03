package iterator;

/**
 * Created by EricLi on 2016/11/18.
 * Email me : EricLi1235@gmail.com.
 */
public class Client {
    public static void main(String[] args){
        IAggregate<String> a = new CusVector<>();
        a.add("1");
        a.add("2");
        a.add("3");
        Iterator<String> i = a.iterator();
        while (i.hasNext()){
            System.out.println(i.next());
        }
    }
}
