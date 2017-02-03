package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by EricLi on 2017/1/5.
 * Email me : EricLi1235@gmail.com.
 */

/**
 * 缓冲器只能容纳普通的字节
 * 在输入的时候进行编码
 * 输出的时候进行解码
 * 具体，就是使用nio包里面的CharSet
 */
public class BufferToText {
    private static final int BSIZE = 1024;
    private static final String FILE_PATH = "src/io/file/data2.txt";
    public static void main(String[] args)throws Exception{
        FileChannel fc = new FileOutputStream(FILE_PATH).getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes()));
        fc.close();
        fc = new FileInputStream(FILE_PATH).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
        buffer.rewind();//返回到数据开始的部分
        String encoding = System.getProperty("file.encoding");
        System.out.println("Decoded using" + encoding + ":" + Charset.forName(encoding).decode(buffer));
        fc = new FileOutputStream(FILE_PATH).getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
        fc.close();
        fc = new FileInputStream(FILE_PATH).getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
        fc = new FileOutputStream(FILE_PATH).getChannel();
        buffer = ByteBuffer.allocate(24);//9个字符，18个字节，剩下的用内容为0的填充
        buffer.asCharBuffer().put("Some text");
        fc.write(buffer);
        fc.close();
        fc = new FileInputStream(FILE_PATH).getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());//会出现三个空白字符

        getDataFromByteBuffer();
    }

    private static void getDataFromByteBuffer(){
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        int i = 0 ;
        while (i++ < bb.limit()){
            if(bb.get() != 0){
                System.out.println("nonzero");
            }
        }
        System.out.println("i = " + i);
        bb.rewind();
        bb.asCharBuffer().put("Howdy!");
        char c;
        while ((c = bb.getChar()) != 0){
            System.out.print(c + " ");
        }
        System.out.println();

        bb.rewind();
        bb.asShortBuffer().put((short)471142);//只有你需要进行类型转换
        System.out.println(bb.getShort());

        bb.rewind();
        IntBuffer ib = bb.asIntBuffer();//视图缓冲器
        ib.put(12345678);
        System.out.println(ib.get());//以此类推，基本类型都支持
        System.out.println(bb.getInt());
    }
}
