package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;


/**
 * Created by EricLi on 2016/11/28.
 * Email me : EricLi1235@gmail.com.
 */
public class TheReplacements {
    private static Test monitor = new Test();
    public static void main(String[] args) throws Exception {
        String s =
                "Here's a block of text to use as input to\n"+
                "the regular expression matcher. Note that we'll\n"+
                "first extract the block of text by looking for\n"+
                "the special delimiters, then process the\n"+
                "extracted block. \n";


//        Pattern pattern = Pattern.compile("///*!(.*)!//*/", Pattern.DOTALL);//匹配个卵，根本不起作用
//        Matcher input = pattern.matcher(s);
//        if(input.find())
//            s = input.group(1);
        s = s.replaceAll(" {2,}", " ");
        s = s.replaceAll("(?m)^ +", "");
        s = s.replaceFirst("[aeiou]", "(VOWEL1)");


        StringBuffer sbuf = new StringBuffer();
        Pattern p = compile("[aeiou]");//找到元音字母
        Matcher m = p.matcher(s);
        while(m.find())
            m.appendReplacement(sbuf, m.group().toUpperCase());//把元音弄成大写的
        m.appendTail(sbuf);
        System.out.println(sbuf);

    }

//    "H(VOWEL1)rE's A blOck Of tExt tO UsE As InpUt tO",
//            "thE rEgUlAr ExprEssIOn mAtchEr. NOtE thAt wE'll",
//            "fIrst ExtrAct thE blOck Of tExt by lOOkIng fOr",
//            "thE spEcIAl dElImItErs, thEn prOcEss thE",
//            "ExtrActEd blOck. "
}
