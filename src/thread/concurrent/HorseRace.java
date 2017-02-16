package thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by EricLi on 2017/2/5.
 * Email me : EricLi1235@gmail.com.
 */
public class HorseRace {

    static class Horse implements Runnable{
        private static int counter = 0;
        private final int id = counter++;
        private int strides = 0;//跑马进度
        private static Random rand = new Random(47);
        //一起向前推进
        private static CyclicBarrier barrier;
        public Horse(CyclicBarrier barrier){
            this.barrier = barrier;
        }

        public synchronized int getStrides(){
            return strides;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    synchronized (this){
                        //随机累计每次跑的进度
                        strides += rand.nextInt(3);
                    }
                    barrier.await();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (BrokenBarrierException e){
                throw new RuntimeException(e);
            }
        }

        @Override
        public String toString() {
            return "Horse " + id + " ";
        }

        public String tracks(){
            StringBuffer s = new StringBuffer();
            for (int i = 0 ; i < getStrides() ; i ++){
                s.append("*");
            }
            s.append(id);
            return s.toString();
        }
    }

    //赛跑进度
    static final int FINISH_LINE = 75;
    private List<Horse> horses = new ArrayList<>();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;
    public HorseRace(int nHorses, final int pause){

        //提供一个栅栏动作
        barrier = new CyclicBarrier(nHorses, new Runnable() {
            @Override
            public void run() {
                StringBuilder s = new StringBuilder();
                for (int i = 0 ; i < FINISH_LINE ; i ++){
                    s.append("=");
                }
                System.out.println(s);
                for (Horse horse : horses){
                    System.out.println(horse.tracks());
                }
                for(Horse horse : horses){
                    if(horse.getStrides() >= FINISH_LINE){
                        System.out.println(horse + "won!");
                        exec.shutdownNow();
                        return;
                    }
                }
                try {
                    //栅栏动作执行以后，睡眠一段时间
                    TimeUnit.MILLISECONDS.sleep(pause);
                }catch (InterruptedException e){
                    System.out.println("barrier-action sleep interrupted");
                }
            }
        });

        for (int i = 0 ; i < nHorses ; i ++){
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args){
        int nHorse = 7;
        int pause = 200;
        if(args.length > 0){
            int n = new Integer(args[0]);
            nHorse = n > 0 ? n : nHorse;
        }

        if (args.length > 1){
            int p = new Integer(args[1]);
            pause = p > -1 ? p : pause;
        }

        new HorseRace(nHorse, pause);
    }
}
