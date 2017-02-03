package io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by EricLi on 2016/12/30.
 * Email me : EricLi1235@gmail.com.
 */
public class TextFile extends ArrayList<String>{

    /**
     * 将文件读进来，变成String
     * @param fileName
     * @return
     */
    public static String read(String fileName){
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
            try {
                String s;
                 while ((s = in.readLine()) != null){
                     sb.append(s);
                     sb.append("\n");
                 }
            }finally {
                in.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 将一段内容写入到file里面
     * @param fileName
     * @param content
     */
    public static void write(String fileName, String content){
        try {
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
            try {
                out.print(content);
            }finally {
                out.close();
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public TextFile(String fileName, String splitter){
        super(Arrays.asList(read(fileName)));
        if(get(0).equals("")){
            remove(0);
        }
    }

    public TextFile(String fileName){
        this(fileName, "\n");
    }

    public void write(String fileName){
        try{
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
            try{
                //本身继承了arraylist
                for(String item : this){
                    out.println(item);
                }
            }finally {
                out.close();
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        String file = read("src/io/TextFile.java");
        write("test.txt", file);
        TextFile text = new TextFile("test.txt");
        text.write("test2.txt");
        TreeSet<String> words = new TreeSet<>(new TextFile("src/io/TextFile.java","\\W+"));
        System.out.println(words.headSet("a").toString());
    }

}
