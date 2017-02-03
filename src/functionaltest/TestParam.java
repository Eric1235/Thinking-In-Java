package functionaltest;

/**
 * Created by EricLi on 2016/12/18.
 * Email me : EricLi1235@gmail.com.
 */
public class TestParam {
    public final int size;
    public final int loops;

    public TestParam(int size, int loops) {
        this.size = size;
        this.loops = loops;
    }

    public static TestParam[] array(int...values){
        int size = values.length / 2;
        TestParam[] result = new TestParam[size];
        int n = 0;
        for(int i = 0 ; i < size ; i ++){
            result[i] = new TestParam(values[n++],values[n++]);
        }
        return result;
    }

    public static TestParam[] array(String[] arrays){
        int[] values = new int[arrays.length];
        for(int i = 0 ; i < values.length ; i ++){
            /**
             * Decodes a {@code String} into an {@code Integer}.
             * Accepts decimal, hexadecimal, and octal numbers given
             * by the following grammar
             */
            values[i] = Integer.decode(arrays[i]);
        }
        return array(values);
    }
}
