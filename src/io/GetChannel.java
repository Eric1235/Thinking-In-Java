package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by EricLi on 2017/1/4.
 * Email me : EricLi1235@gmail.com.
 */
public class GetChannel {
    private static final int BUFFER_SIZE = 1024 * 4;

    public static void main(String[] args) throws Exception{
        FileChannel fileChannel = new FileOutputStream("data.txt").getChannel();
        fileChannel.write(ByteBuffer.wrap("Some txt".getBytes()));
        fileChannel.close();
  
        fileChannel = new RandomAccessFile("data.txt", "rw").getChannel();
        fileChannel.position(fileChannel.size());//移动到末尾
        fileChannel.write(ByteBuffer.wrap("Some more".getBytes()));
        fileChannel.close();

        fileChannel = new FileInputStream("data.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        fileChannel.read(buffer);
        buffer.flip();
        while (buffer.hasRemaining()){
            System.out.println((char)buffer.get());
        }
        channelCopy(args);
    }

    /**
     * 通过通道来写文件
     * @param args
     * @throws Exception
     */
    private static void channelCopy(String[] args) throws Exception{
        FileChannel in = new FileInputStream(args[0]).getChannel();
        FileChannel out = new FileOutputStream(args[1]).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        while (in.read(buffer) != -1){
            buffer.flip();//做好被读的准备
            out.write(buffer);
            buffer.clear();//清空
        }
        in.close();
        out.close();
    }
}
