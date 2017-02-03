package io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * Created by EricLi on 2016/12/30.
 * Email me : EricLi1235@gmail.com.
 */
public class FormattedMemoryInput {
    public static void main(String[] args)throws IOException{
        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(BufferedInputFile.read("src/io/MemoryInput.java").getBytes()));
            //在没有阻塞的情况下所能读取的字节数
            while (true){
                //会出现中文乱码
                System.out.print((char)in.readByte());
//                System.out.print(in.readUTF());//都已经完全转换成为byte了，我们是不可能读取到String的
            }
        }catch (EOFException e){
            System.err.println("End of stream");
        }
    }
}
