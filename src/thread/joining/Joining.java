package thread.joining;

/**
 * Created by EricLi on 2017/1/13.
 * Email me : EricLi1235@gmail.com.
 */
public class Joining {

    static class Sleeper extends Thread{
        private int duration;
        public Sleeper(String name, int sleepTime){
            super(name);
            duration = sleepTime;
            start();
        }

        @Override
        public void run() {
            try {
                sleep(duration);
            }catch (InterruptedException e){
                System.out.println(getName() + " was interrupted." +
                "isInterrupted()" + isInterrupted());
                return;
            }
            System.out.println(getName() + " has awakened");
        }
    }

    static class Joiner extends Thread{
        private Sleeper sleeper;
        public Joiner(String name, Sleeper sleeper){
            super(name);
            this.sleeper = sleeper;
            start();
        }

        @Override
        public void run() {
            try {
                sleeper.join();
//                System.out.println("doWork");
            }catch (InterruptedException e){
                System.out.println("Interrupted");
            }

            System.out.println(getName() + " join completed");
        }
    }

    public static void main(String[] args){
        Sleeper sleepy = new Sleeper("Sleepy", 1500);
        Sleeper grumpy = new Sleeper("Grumpy", 1500);
        Joiner dopey = new Joiner("Dpoey", sleepy);
        Joiner doc = new Joiner("Doc", grumpy);
        grumpy.interrupt();//会立即走入中断异常

    }
}
