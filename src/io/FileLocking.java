package io;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * Created by EricLi on 2017/1/5.
 * Email me : EricLi1235@gmail.com.
 */
public class FileLocking {
    public static void main(String[] args)throws Exception{
        FileOutputStream fos = new FileOutputStream("src/io/file/file.txt");
        //使用文件锁
        FileLock fl = fos.getChannel().tryLock();//对整个文件上锁
        //对文件进行操作的时候，阻塞其他线程操作文件
        if(fl != null){
            System.out.println("Locked file");
            TimeUnit.MILLISECONDS.sleep(10000);
            fl.release();
            System.out.println("Released file");
        }
        fos.close();
    }
}
