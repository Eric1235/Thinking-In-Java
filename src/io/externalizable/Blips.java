package io.externalizable;

import java.io.*;

/**
 * Created by EricLi on 2017/1/6.
 * Email me : EricLi1235@gmail.com.
 */
public class Blips {
    private static final String FILE_PATH = "src/io/file/Blips.out";
    public static void main(String[] args)throws IOException, ClassNotFoundException{
        Blip1 blip1 = new Blip1();
        Blip2 blip2 = new Blip2();
        Blip3 blip3 = new Blip3(1,"a");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH));

        out.writeObject(blip1);
//        out.writeObject(blip2);
        out.writeObject(blip3);

        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH));
        System.out.println("Recovering Objects");
        //恢复的过程，会调用类的所有默认构造方法，然后调用readExternal
        blip1 = (Blip1) in.readObject();
//        blip2 = (Blip2) in.readObject();
        blip3 = (Blip3) in.readObject();
        System.out.println("Blip3 =" + blip3);

    }
}
