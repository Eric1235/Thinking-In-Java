package io.myworld;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EricLi on 2017/1/6.
 * Email me : EricLi1235@gmail.com.
 */
public class MyWorld {
    public static void main(String[] args)throws IOException, ClassNotFoundException{
        House house = new House();
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Bosco the dog", house));
        animals.add(new Animal("Ralph the hamster", house));
        animals.add(new Animal("Molly the cat", house));

        ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
        ObjectOutputStream o1 = new ObjectOutputStream(buf1);
        o1.writeObject(animals);
        o1.writeObject(animals);

        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        ObjectOutputStream o2 = new ObjectOutputStream(buf2);
        o2.writeObject(animals);

        ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()));

        List animals1 = (List) in1.readObject();
        List animals2 = (List) in1.readObject();
        List animals3 = (List) in2.readObject();

        System.out.println("animals" + animals);

        //animals1,animals2出现了相同的地址，都是指向一个house对象的引用
        System.out.println("animals1" + animals1);
        System.out.println("animals2" + animals2);
        //恢复animals3的时候，系统无法知道另外一个流的对象使第一个流的对象的别名，所以会产生出完全不同的对象网
        System.out.println("animals3" + animals3);
    }
}
