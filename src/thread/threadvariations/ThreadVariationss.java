package thread.threadvariations;

/**
 * Created by EricLi on 2017/1/13.
 * Email me : EricLi1235@gmail.com.
 */

import java.util.concurrent.TimeUnit;

/**
 * 使用内部类将线程隐藏在代码中
 * Runnable就是一个任务
 */
public class ThreadVariationss {

    static class InnerThread1{
        private int countDown = 5;
        private Inner inner;//其他方法可能需要访问这个内部类

        public InnerThread1(String name){
            new Inner(name);
        }
        private class Inner extends Thread{
            Inner(String name){
                super(name);
                start();
            }

            @Override
            public String toString() {
                return getName() + ": " + countDown;
            }

            @Override
            public void run() {
                try {
                    while (true){
                        System.out.println(this);
                        if(--countDown == 0){
                            return;
                        }
                        sleep(10);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    static class InnerThread2{
        private int countDown = 5;
        private Thread t;//在构造方法里面接收
        public InnerThread2(String name){
            //创建一个匿名的thread，并且给到外部t，那么外部引用，就直接使用t就好了
            t = new Thread(name){
                @Override
                public void run() {
                    try {
                        while (true){
                            System.out.println(this);
                            if(--countDown == 0){
                                return;
                            }
                            sleep(10);
                        }
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }
    }

    static class InnerRunnable1{
        private int countDown = 5;
        private Inner inner;//其他方法需要访问这个内部类

        public InnerRunnable1(String name){
            inner = new Inner(name);
        }

        private class Inner implements Runnable{
            Thread t;
            Inner(String name){
                t = new Thread(this,name);
                t.start();
            }


            @Override
            public String toString() {
                return t.getName() + ": " + countDown;
            }

            @Override
            public void run() {
                while (true){
                    try {
                        System.out.println(this);
                        if(--countDown == 0){
                            return;
                        }
                        TimeUnit.MILLISECONDS.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class InnerRunnable2{
        private int countDown = 5;
        private Thread t;


        public  InnerRunnable2(String name){
            t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            System.out.println(this);
                            if(--countDown == 0){
                                return;
                            }
                            TimeUnit.MILLISECONDS.sleep(100);
                        }
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
                public String toString(){
                    return Thread.currentThread().getName() + ": " + countDown;
                }
            },name);
            t.start();
        }
    }

    static class ThreadMethod{
        private int countDown = 5;
        private Thread t;
        private String name;

        public ThreadMethod(String name) {
            this.name = name;
        }

        //方法内部创建线程
        public void runTask(){
            if(t == null){
                t = new Thread(name){
                    @Override
                    public void run() {
                        try {
                            while (true){
                                System.out.println(this);
                                if(--countDown == 0){
                                    return;
                                }
                                TimeUnit.MILLISECONDS.sleep(100);
                            }
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    public String toString(){
                        return getName() + ": " + countDown;
                    }

                };
                t.start();
            }
        }
    }

    public static void main(String[] args){
        new InnerThread1("InnerThread1");
        new InnerThread2("InnerThread2");
        new InnerRunnable1("InnerRunnable1");
        new InnerRunnable2("InnerRunnable2");
        new ThreadMethod("").runTask();
    }
}
