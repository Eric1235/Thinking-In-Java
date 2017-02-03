package regex;

/**
 * Created by EricLi on 2016/11/25.
 * Email me : EricLi1235@gmail.com.
 */
public class Constants {
    /**
     * 前面是三个数字，
     */
    public static final String MATCH_PHONE_NUMBER = "(\\(\\d{3})\\)|\\d{3})\\s?\\d{3}(-|)?\\d{4}";

    public static final String MATCH_T = "t[abcde]n";//第二个字符是限定方括号里面的其中一个才能够匹配

    public static final String MATCH_T1 = "t(a|b|c|d|e)n";//要去使用圆括号，因为方括号只能匹配一个字符

    public static final String MATCH_T2 = "t.n";//这样子就是代表匹配任何字符

    public static final String MATCH_CODE = "[0-9]{3}-[0-9]{2}-[0-9]{4}";

    public static final String MATCH_CODE1 = "\\d{3}-?[0-9]{2}-?[0-9]{4}";//连字符变成是可选的

    public static final String MATCH_DATE = "[a-z]+ \\s + \\d{1,2}, \\s* \\d{4}";//匹配诸如 Mar 22,1993这样的出生日期。
}
