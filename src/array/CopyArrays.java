package array;

import java.util.Arrays;

/**
 * Created by EricLi on 2016/12/11.
 * Email me : EricLi1235@gmail.com.
 * 复制的时候，绝对不能出现越界
 */
public class CopyArrays {
    public static void main(String[] args){
        int[] i = new int[7];
        int[] j = new int[10];
        Arrays.fill(i,47);
        Arrays.fill(j,99);
        System.arraycopy(i,0,j,0,i.length);
        System.out.println("j=" + Arrays.toString(j));
        int[] k = new int[15];
        Arrays.fill(k,103);
        System.arraycopy(i,0,k,0,k.length);//这样就越界了哦
        System.out.println("k=" + Arrays.toString(k));//果然越界了

    }
}
