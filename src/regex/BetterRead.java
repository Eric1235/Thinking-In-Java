package regex;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by EricLi on 2016/11/27.
 * Email me : EricLi1235@gmail.com.
 */
public class BetterRead {
    public static void main(String[] args){
        //一般情况下，Scanner根据空白符进行分词，但是我们可以自定义正则表达式作为定界符。

        String splitRegex = "\\s*,\\s*";//使用逗号，或者加上逗号前面后面的空白符来进行分割
        Pattern pattern = Pattern.compile(splitRegex);

        Scanner input = new Scanner(SimpleRead.input);//可以接受任何类型的输入对象，File,Stream,String,Readable
//        input.useDelimiter(splitRegex);
//        input.useDelimiter(pattern);
        String name = input.nextLine();
        System.out.println(name);
        String numbers = input.nextLine();
        System.out.println(numbers);
        String[] numArrays = numbers.split(" ");
        int age = Integer.parseInt(numArrays[0]);
        double favorite = Double.parseDouble(numArrays[1]);
        System.out.println("age = " + age);
        System.out.format("My favorite double is %f", favorite/2);

    }
}
