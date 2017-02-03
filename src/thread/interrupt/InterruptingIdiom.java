package thread.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * Created by EricLi on 2017/1/22.
 * Email me : EricLi1235@gmail.com.
 */
public class InterruptingIdiom {

    static class NeedsClearup{
        private final int id;

        public NeedsClearup(int id) {
            this.id = id;
            System.out.println("NeedsClearUp " + id);
        }

        public void clearUp(){
            System.out.println("Clearing up " + id);
        }
    }

    //演示正确去编写合适的代码，使得线程无论何时退出，资源总能得到正确回收
    static class Blocked3 implements Runnable{
        private volatile double d = 0.0;
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    //point1
                    NeedsClearup n1 = new NeedsClearup(1);
                    try {
                        System.out.println("Sleeping");
                        TimeUnit.SECONDS.sleep(1);
                        //point2
                        NeedsClearup n2 = new NeedsClearup(2);
                        try {
                            System.out.println("Calculating");
                            for(int i = 0 ; i < 2500 ; i ++){
                                d = d + (Math.PI + Math.E) / d;
                            }
                            System.out.println("Finished time-consuming operation");
                        }finally {
                            n2.clearUp();
                        }
                    } finally {
                        n1.clearUp();
                    }
                    System.out.println("Exiting via while() test");
                }
            }catch (InterruptedException e){
                System.out.println("Exiting via Interruption");
            }

        }
    }

    public static void main(String[] args)throws Exception{

        if(args.length != 1){
            System.out.println("useage : java InterruptingIdom delay-in-ms");
            System.exit(1);
        }
        Thread t = new Thread(new Blocked3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
        t.interrupt();
    }

}
