package io.serializable;

import java.io.*;
import java.util.Random;

/**
 * Created by EricLi on 2017/1/6.
 * Email me : EricLi1235@gmail.com.
 */
public class Worm implements Serializable {
    private static Random rand = new Random(47);

    private static final String FILE_PATH = "src/io/file/worm.out";

    private Data[] d = {
            new Data(rand.nextInt(10)),
            new Data(rand.nextInt(10)),
            new Data(rand.nextInt(10))
    };

    private Worm next;
    private char c;
    public Worm(int i, char x){
        System.out.println("Worm constructor:" + i);
        c = x;
        if(--i > 0){
            next = new Worm(i, (char)(x + 1));
        }
    }

    public Worm(){
        System.out.println("Default constructor");
    }

    public String toString(){
        StringBuilder result = new StringBuilder(":");
        result.append(c);
        result.append("(");
        for (Data data : d){
            result.append(data);
        }
        result.append(")");
        if(next != null){
            result.append(next);
        }

        return result.toString();
    }

    public static void main(String[] args)throws IOException,ClassNotFoundException{
        Worm w = new Worm(6, 'a');
        System.out.println(w);

//        //写到文件
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
//        out.writeObject("Worm storage\n");
//        out.writeObject(w);
//        out.close();
        //读回来
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH));
        String s = (String)in.readObject();
        Worm w2 = (Worm)in.readObject();
        System.out.println(s + "w2=" + w2);

        //写到流
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(bout);
        out2.writeObject("Worm storage\n");
        out2.writeObject(w);
//        out2.flush();

        //读回来
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));

        String s1 = (String)in2.readObject();
        Worm w3 = (Worm)in2.readObject();
        System.out.println(s1 + "w3=" + w3);
    }
}
