package io;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by EricLi on 2016/12/29.
 * Email me : EricLi1235@gmail.com.
 */
public class MemoryInput {
    public static void main(String[] args)throws IOException{
        //把整个文件变成了一个String对象
        StringReader in = new StringReader(BufferedInputFile.read("src/collection/SlowMap.java"));
        int c;
        while ((c = in.read()) != -1){
            //要注意，读进来的都是int，要转型为char才能正确输出
            System.out.print((char) c);
        }
    }
}
