package io;

import java.io.*;

/**
 * Created by EricLi on 2016/12/30.
 * Email me : EricLi1235@gmail.com.
 */

/**
 * 如果我们使用DataInputStream去写入数据，java保证我们可以使用DataOutputStream准确地读取数据，无论读和写数据的平台有多么不同
 * 这一点很有价值
 */
public class StoringAndRecoveringData {
    public static void main(String[] args) throws IOException{
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Data.txt")));
        out.writeDouble(3.14159);
        out.writeUTF("That is pi");
        out.writeDouble(1.41413);
        out.writeUTF("Square root of 2");
        //输出的东西会有乱码
        out.close();

        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("Data.txt")));
        //顺序是不能打乱的，不然会出错
        System.out.println(in.readDouble());

        System.out.println(in.readUTF());
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
    }
}
