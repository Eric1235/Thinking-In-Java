package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by EricLi on 2016/11/24.
 * Email me : EricLi1235@gmail.com.
 */
public class Test {
    public static void main(String[] args){
        String[] strings = new String[]{"^Java",//^代表一行的开始
                "\\Breg.*", //非词的边界
                "n.w\\s+h(a|i)s",//反斜杠s代表空白字符
                //"s?",//一个或者0个s,会不小心匹配到空白字符串的啦
                //"s*",//是后面跟0个或多个字符,会不小心匹配到空白字符串的啦
                "s+",//发现s 1个或者多个
                "s{4}",//发现s 4次，目前这个是没有匹配到的
                "s{1}.",//发现s 1次，后面加任意字符
                "(ss)+"
                //"s{0,3}"//发现s 0到3次,会不小心匹配到空白字符串的啦
                };
        String s = "Java now has regular expressions";

        for(String arg : strings){
            System.out.println("arg=" + arg );
            Pattern p = Pattern.compile(arg);//编译后的正则表达式
            Matcher matcher = p.matcher(s);
            while (matcher.find()){
                System.out.println("Match\"" + matcher.group() + "\" at positions " +
                matcher.start() + "-" + (matcher.end() - 1));
            }
        }
    }
}
