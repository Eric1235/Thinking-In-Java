package io;
import java.io.*;
/**
 * Created by EricLi on 2016/12/30.
 * Email me : EricLi1235@gmail.com.
 */
public class Redirecting {
    public static void main(String[] args)throws IOException{
        PrintStream console = System.out;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("src/io/Redirecting.java"));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("test.out")));
        //io重定向，操作的是字节流
        //重定向的是字节流，不是字符流
        System.setIn(in);//把这些流给嫁接过去
        System.setOut(out);
        System.setErr(out);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = br.readLine()) != null && s.length() != 0){
            System.out.println(s);
        }
        out.close();
        System.setOut(console);
    }
}
