package thread.cooperation;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by EricLi on 2017/2/5.
 * Email me : EricLi1235@gmail.com.
 */
public class PipedIO {

    static class Sender implements Runnable{
        private Random random = new Random(47);

        private PipedWriter out = new PipedWriter();

        public PipedWriter getPipedWriter() {
            return out;
        }

        @Override
        public void run() {
            try {
                while (true){
                    for (char c = 'A'; c <= 'z'; c++){
                        out.write(c);
                        TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                    }
                }
            }catch (IOException e){
                System.out.println(e + " Sender write exception");
            }catch (InterruptedException e){
                System.out.println(e + " Sender sleep interrupted");
            }
        }
    }

    static class Receiver implements Runnable{
        private PipedReader in;
        public Receiver(Sender sender) throws IOException{
            //需要进行关联
            in = new PipedReader(sender.getPipedWriter());
        }
        @Override
        public void run() {
            try {
                while (true){
                    System.out.print("Read: " + (char) in.read() + ",");
                }
            }catch (IOException e){
                System.out.println(e + "Receiver read exception");
            }
        }
    }

    public static void main(String[] args)throws Exception{
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService exec = Executors.newCachedThreadPool();
        //哪个先执行都一样，会自动阻塞挂起
        exec.execute(receiver);
        exec.execute(sender);

        TimeUnit.SECONDS.sleep(10);
        exec.shutdownNow();
    }
}
