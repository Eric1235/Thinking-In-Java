package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by EricLi on 2016/11/24.
 * Email me : EricLi1235@gmail.com.
 */
public class Finding {
    public static void main(String[] args){
        Matcher m = Pattern.compile("\\w+")//划分为单词
                .matcher("Evening is full of the linnet's wings");
        while (m.find()){
            System.out.print(m.group() +" ");
        }
    }
}
