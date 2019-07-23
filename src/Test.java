/**
 * Created by EricLi on 2017/2/21.
 * Email me : EricLi1235@gmail.com.
 */
public class Test {

    static class Dog{
        public void A(){
            System.out.println(this + " A");
        }

        public void B(){
            System.out.println(this + " B");
        }

        @Override
        public String toString() {
            return "Dog";
        }
    }

    static class Woof extends Dog{

        public void A() {
            System.out.println(this + " A");
        }

        public void B(){
            System.out.println(this + " B");
        }

        public void hunt(){
            System.out.println(this +" hunt");
        }

        @Override
        public String toString() {
            return "Woof ";
        }
    }

    public static void main(String[] args){
        Dog dog = new Woof();
        dog.A();
        dog.B();
        Woof w = (Woof)dog;
        w.A();
        w.B();
        w.hunt();
    }
}
