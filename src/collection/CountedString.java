package collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by EricLi on 2016/12/18.
 * Email me : EricLi1235@gmail.com.
 */
public class CountedString {
    //内部只用一份表去维护
    private static List<String> created = new ArrayList<>();

    private String s;
    private int id = 0;

    public CountedString(String str) {
        this.s = str;
        created.add(s);
        for(String s2 : created){
            if(s2.equals(s)){
                id++;
            }
        }
    }

    @Override
    public String toString() {
        return "String: " +s + " id:" + id + " hashCode: " + hashCode();
    }

    /**
     * 对每一个有意义的域，都去计算出散列码
     * 合并计算，得到散列码
     * 检查返回值，确保相同的对象可以得到相同的散列码
     * @return
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + s.hashCode();
        result = 37 * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CountedString && s.equals(((CountedString) obj).s) &&
                id == (((CountedString) obj).id);
    }

    public static void main(String[] args){
        Map<CountedString, Integer> map = new HashMap<>();
        CountedString[] cs = new CountedString[5];
        for(int i = 0 ; i < 5 ; i++) {
            cs[i] = new CountedString("hi");
            map.put(cs[i], i);
        }
        System.out.println(map);
        for(CountedString s : cs){
            System.out.println("Looking up:" + s);
            System.out.println(map.get(s));
        }
    }
}
