package regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by EricLi on 2016/11/27.
 * Email me : EricLi1235@gmail.com.
 */
public class SimpleRead {
    public static BufferedReader input = new BufferedReader(
            new StringReader("Sir Robin of Camelot\n22 1.61803"));//BufferedReader对象有提供了readLine()方法，可以读取一行的文本
    public static void main(String[] args){
        try {
            String name = input.readLine();
            System.out.println(name);
            String numbers = input.readLine();
            System.out.println(numbers);
            String[] numArrays = numbers.split(" ");
//            String[] numArrays = numbers.split(" ",3);//这个版本呢，就是可以限定去分割多少次，有什么作用呢？节能吧
            int age = Integer.parseInt(numArrays[0]);
            double favorite = Double.parseDouble(numArrays[1]);
            System.out.println("age = " + age);
            System.out.format("My favorite double is %f", favorite / 2);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
