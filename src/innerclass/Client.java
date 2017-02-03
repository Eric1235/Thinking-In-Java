package innerclass;


/**
 * Created by EricLi on 2016/11/18.
 * Email me : EricLi1235@gmail.com.
 */
public class Client {
    public static void main(String[] args){
        Test test = new Test(5);

        Test.Counter counter = test.count();
        int[] a = new int[100];

        for(int i = 0 ; i < 100 ; i ++){
            a[i] = i;
        }
        System.out.println(counter.sum(a));
        System.out.println(counter.sumTest(5));
        Test.Max max = test.max();

    }
}
