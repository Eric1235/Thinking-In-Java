package regex;

import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Created by EricLi on 2016/11/27.
 * Email me : EricLi1235@gmail.com.
 */
public class ThreadAnalyzer {
    static String threatData =
            "58.27.82.161@01/10/2005\n"+
            "36.43.225.54@06/08/2008\n"+
            "104.160.34.110@27/11/2016\n"+
            "192.168.2.1@26/11/2016\n"+
            "[Next log section with different data format]";

    public static void main(String[] args){
        Scanner scanner = new Scanner(threatData);

        /**
         * 可以，将这两个查询杂糅到一起，是要两个同时生效才有的吧
         * 要是分开，那就是独立生效了
         */
        String pattern = "(\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3})@" + "(\\d+/\\d+/\\d+)";
        while (scanner.hasNext(pattern)){
            scanner.next(pattern);
            MatchResult matchResult = scanner.match();
            String ip = matchResult.group(1);//0是代表全集哦
            String date = matchResult.group(2);
            System.out.println("group 1 start = " + matchResult.start(1) + " group 1 end = " + matchResult.end(1));
            System.out.println("group 2 start = " + matchResult.start(2) + " group 2 end = " + matchResult.end(2));
            System.out.format("Thread on %s from %s\n", date, ip);
        }
    }
}
