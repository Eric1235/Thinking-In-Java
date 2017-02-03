package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by EricLi on 2016/12/29.
 * Email me : EricLi1235@gmail.com.
 */
public class BufferedInputFile {
    public static String read(String filename) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null){
            //需要手动去加换行符
            sb.append(s + "\n");
        }
        in.close();
        return sb.toString();
    }

    public static LinkedList<String> readAndreverse(String filename) throws IOException{
        LinkedList<String> list = new LinkedList<>();
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        while ((s = in.readLine()) != null){
            list.add(s);
        }
        in.close();
        return list;
    }

    public static void main(String[] args)throws IOException{
        System.out.println(read("src/io/BufferedInputFile.java"));
        LinkedList<String> list = readAndreverse("src/collection/Groundhog.java");
        for(int i = list.size()-1 ; i>=0 ; i--){
            System.out.println(list.get(i));
        }
    }
}
