package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by EricLi on 2016/12/30.
 * Email me : EricLi1235@gmail.com.
 */

/**
 * 使用BufferedReader将输入流接入，然后直接输出到控制台
 */
public class Echo {
    public static void main(String[] args)throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = in.readLine()) != null && s.length() != 0){
            System.out.println(s);
        }
        print();
    }

    public static void print(){
        PrintWriter out = new PrintWriter(System.out,true);
        out.println("Hello world!");
    }
}
