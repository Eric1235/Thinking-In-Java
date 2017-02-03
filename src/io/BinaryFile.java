package io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by EricLi on 2016/12/30.
 * Email me : EricLi1235@gmail.com.
 */
public class BinaryFile {
    public static void main(String[] args)throws IOException{
        System.out.println(read(new File("src/io/TextFile.java").getAbsoluteFile()).length);
    }

    public static byte[] read(File bFile)throws IOException{
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(bFile));
        try {
            byte[] data = new byte[in.available()];
            in.read(data);
            return data;
        }finally {
            in.close();
        }
    }

    public static byte[] read(String fileName)throws IOException{
        /**
         * 这样才能拿到真正file，将路径转换成绝对路径以后再去拿
         *  /**
         * Returns the absolute form of this abstract pathname.  Equivalent to
         * <code>new&nbsp;File(this.{@link #getAbsolutePath})</code>.
         *
         * @return  The absolute abstract pathname denoting the same file or
         *          directory as this abstract pathname
         *
         * @throws  SecurityException
         *          If a required system property value cannot be accessed.
         *
         * @since 1.2
         * public File getAbsoluteFile() {
                String absPath = getAbsolutePath();
                return new File(absPath, fs.prefixLength(absPath));
            }
         */
        return read(new File(fileName).getAbsoluteFile());
    }
}
