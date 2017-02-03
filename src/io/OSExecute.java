package io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by EricLi on 2017/1/4.
 * Email me : EricLi1235@gmail.com.
 */
public class OSExecute {

    public static void command(String command){
        boolean err = false;
        try{
            //执行一个新进程
            Process process = new ProcessBuilder(command.split(" ")).start();
            BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = results.readLine()) != null){
                System.out.println(s);
            }

            BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((s = errors.readLine()) != null){
                System.out.println(s);
                err = true;
            }
        }catch (Exception e){
            if(!command.startsWith("CMD /C")){
                command("CMD /C " + command);
            }else {
                throw new RuntimeException(e);
            }
        }

        if (err){
            throw new RuntimeException("Errors executing :" + command);
        }
    }
}
