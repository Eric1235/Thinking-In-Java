package io;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by EricLi on 2017/1/5.
 * Email me : EricLi1235@gmail.com.
 */

/**
 * 最大可以创建2gb的文件
 */
public class MappedIO {
    private static int numOfints = 4000000;
    private static int numOfbufferints = 200000;

    private static final String FILE_PATH = "src/io/file/temp.tmp";

    private abstract static class Tester{
        private String name;

        public Tester(String name) {
            this.name = name;
        }

        public void runTest(){
            System.out.println(name + ":");
            try {
                long start = System.nanoTime();
                test();
                double duration = System.nanoTime() - start;
                System.out.format("%.2f\n", duration / 1.0e9);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        public abstract void test() throws IOException;
    }

    private static Tester[] tests = {
            new Tester("Stream Write") {
                @Override
                public void test() throws IOException {
                    DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(
                            new FileOutputStream(new File(FILE_PATH))));
                    for (int i = 0 ; i < numOfints ; i ++){
                        dos.writeInt(i);
                    }
                    dos.close();
                }
            },

            new Tester("Mapped Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile(FILE_PATH,"rw").getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
                    for (int i = 0 ; i < numOfints ; i ++){
                        ib.put(i);
                    }
                    fc.close();
                }
            },

            new Tester("Stream Read") {
                @Override
                public void test() throws IOException {
                    DataInputStream dis = new DataInputStream(
                            new BufferedInputStream(new FileInputStream(FILE_PATH)));
                    for (int i = 0 ; i < numOfints ; i ++){
                        dis.readInt();
                    }
                    dis.close();
                }
            },

            new Tester("Mapped Read") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new FileInputStream(new File(FILE_PATH)).getChannel();
                    //模式，位置，长度
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).asIntBuffer();
                    while (ib.hasRemaining()){
                        ib.get();
                    }
                    fc.close();
                }
            },

            new Tester("Stream Read/Write") {
                @Override
                public void test() throws IOException {
                    RandomAccessFile raf = new RandomAccessFile(new File(FILE_PATH), "rw");
                    raf.writeInt(1);
                    for (int i = 0 ; i < numOfbufferints ; i ++){
                        raf.seek(raf.length() - 4);
                        raf.writeInt(raf.readInt());
                    }
                    raf.close();
                }
            },

            new Tester("Mapped Read/Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile(new File(FILE_PATH), "rw").getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
                    ib.put(0);
                    for (int i = 1 ; i < numOfbufferints ; i ++){
                        ib.put(ib.get(i-1));
                    }
                    fc.close();
                }
            }
    };

    public static void main(String[] args){
        for (Tester tester : tests){
            tester.runTest();
        }
    }
}
