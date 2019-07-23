package collection;

import java.util.*;

/**
 * Created by EricLi on 2016/12/12.
 * Email me : EricLi1235@gmail.com.
 */
public class UnSupport {
    static void test(String msg, List<String> list){
        System.out.println("msg=" + msg);

        Collection<String> c = list;

        Collection<String> subList = list.subList(1,8);

        Collection<String> c2 = new ArrayList<>(subList);
        try{
            c.retainAll(c2);
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            c.clear();
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            c.retainAll(c2);
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            c.add("X");
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            c.addAll(c2);
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            c.remove("C");
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            list.set(0, "X");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        //只要记住，这时生成的数组是不可变数组，不能执行可变的操作
        List<String> list = Arrays.asList("A B C D E F G H I J K".split(" "));
        test("Modifiable Copy", new ArrayList<>(list));
//        test("Arrays.asList()",list);

        //产生一组不可变的列表，根本不能执行移除操作
        test("unmodifiableList()", Collections.unmodifiableList(new ArrayList<>(list)));
    }
}
