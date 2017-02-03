package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * Created by EricLi on 2016/12/30.
 * Email me : EricLi1235@gmail.com.
 */
public class FileOutputShortcut {
    static String file = "FileOutputShortcut.out";
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read("src/io/FileOutputShortcut.java")));
        //如果文件不存在，会主动创建文件
        PrintWriter out = new PrintWriter(file);
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null){
            //这里记录行号
            out.println(lineCount++ + ":" + s);
        }
        out.close();
        System.out.println(BufferedInputFile.read(file));

    }
}
