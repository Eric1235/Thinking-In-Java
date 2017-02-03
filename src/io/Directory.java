package io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import static io.Directory.TreeInfo.walk;

/**
 * Created by EricLi on 2016/12/25.
 * Email me : EricLi1235@gmail.com.
 */
public final class Directory {

    public static File[] local(File dir, final String regex){

        //使用过滤器去过滤文件名
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }

    public static File[] local(String path, String regex){
        return local(new File(path), regex);
    }

    public static class TreeInfo implements Iterable<File>{

        public List<File> files = new ArrayList<>();
        public List<File> dirs = new ArrayList<>();

        void addAll(TreeInfo treeInfo){
            files.addAll(treeInfo.files);
            dirs.addAll(treeInfo.dirs);
        }

        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }

        public static TreeInfo work(String start, String regex){
            return resourceDirs(new File(start), regex);
        }

        public static TreeInfo work(File start, String regex){
            return resourceDirs(start, regex);
        }

        public static TreeInfo walk(File start){
            return resourceDirs(start, "*");
        }

        public static TreeInfo walk(String start){
            return resourceDirs(new File(start),".*");
        }

        /**
         * 目的就是要把目录下面的文件和文件夹都遍历出来，统一放到两个list下面
         * @param startFile
         * @param regex
         * @return
         */
        static TreeInfo resourceDirs(File startFile, String regex){
            TreeInfo result = new TreeInfo();
            for(File item : startFile.listFiles()){
                if(item.isDirectory()){
                    result.dirs.add(item);
                    //递归调用
                    result.addAll(resourceDirs(item, regex));
                }else if(item.getName().matches(regex)){
                    result.files.add(item);
                }
            }
            return result;
        }
    }

    public static void main(String[] args){
        if(args.length == 0){
            //可以直接调用内部类的方法
            System.out.println(walk("."));
        }else {
            for (String arg : args){
                System.out.println(walk(arg));
            }
        }
    }

}
