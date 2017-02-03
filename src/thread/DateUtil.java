package thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by EricLi on 2016/12/26.
 * Email me : EricLi1235@gmail.com.
 */
public class DateUtil {
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<>();
    private static Map<String, SimpleDateFormat> SDFMap = new HashMap<>();

    private static final String MDHMSS = "MMddHHmmssSSS";
    private static final String YMDHMS = "yyyyMMddHHmmss";
    private static final String YMDHMS1 = "yyyy-MM-dd-HH:mm:ss";
    private static final String YMD = "yyMMdd";//这个不能生效
    private static final String YMD1 = "yyyy-MM-dd";
    private static final String HMS = "HHmmss";

    //获取pattern
    private static SimpleDateFormat getSdf(final String pattern){
        ThreadLocal<SimpleDateFormat> sdfThread = sdfMap.get(pattern);
        if(sdfThread == null){
            synchronized (DateUtil.class){
                //从哈希表中得到pattern
                sdfThread = sdfMap.get(pattern);
                //当哈希表中没有数据时
                if(sdfThread == null){
                    System.out.println("sdfMap null, put a new one");
                    sdfThread = new ThreadLocal<SimpleDateFormat>(){
                        //get不到的时候，就会去调用这个方法
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, sdfThread);
                }
            }
        }
        return sdfThread.get();
    }

    //第二种方法，并没有给每个线程一个副本
    private static SimpleDateFormat getSDF(String pattern){
        SimpleDateFormat simpleDateFormat = SDFMap.get(pattern);
        if (simpleDateFormat == null){
            simpleDateFormat = new SimpleDateFormat(pattern);
            SDFMap.put(pattern, simpleDateFormat);
        }
        return simpleDateFormat;
    }

    public static Date parseDate(String date, String pattern){
        if(date == null){
            throw new IllegalArgumentException("The date must not be null");
        }
        try {
            return getSdf(pattern).parse(date);
        }catch (ParseException e){
            e.printStackTrace();
            System.out.println("不支持的解析格式");
        }
        return null;
    }

    public static String formatDate(Date date, String pattern){
        if(date == null){
            throw new IllegalArgumentException("The date must not be null");
        }else {
            return getSDF(pattern).format(date);
        }
    }

    /**
     * 字节数组转换成16进制
     * @param bytes
     * @return
     */
    private static String bytesToHexString(byte[] bytes){
        StringBuilder builder = new StringBuilder();
        for(int i = 0 ; i < bytes.length ; i ++){
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if(hex.length() == 1){
                builder.append(0);
            }
            builder.append(hex);
        }
        return builder.toString();
    }

    public static void main(String[] args){
        String date = DateUtil.formatDate(new Date(),YMDHMS1);
        System.out.println(date);

        new Thread(()->{
            String date1 = DateUtil.formatDate(new Date(),YMDHMS1);
            System.out.println(date1);
        }).start();
        new Thread(()->{
            String date2 = DateUtil.formatDate(new Date(),YMDHMS1);
            System.out.println(date2);
        }).start();

        String s = "hello";
        byte[] bytes = s.getBytes();
//        System.out.println(bytes.length);
        for (int i = 0 ; i < bytes.length ; i ++){
//            System.out.println(bytes[i]);
        }
//        System.out.println(bytesToHexString(s.getBytes()));

    }
}
