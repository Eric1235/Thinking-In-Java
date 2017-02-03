package io;

/**
 * Created by EricLi on 2017/1/2.
 * Email me : EricLi1235@gmail.com.
 */
public class Base {
    public String name ="name";
    public void callName(){
        System.out.println("name = " + name);
    }

    public Base(){
        callName();
    }

    static class Test extends Base{
        public String name ="Test";
        public void callName(){
            System.out.println("name = " + name);
        }
    }

    public static void main(String[] args){
        Test test = new Test();
        test.callName();

    }
}
