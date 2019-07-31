package sort;

/**
 * @author lixinkun
 * date: 2019-07-25 10:29
 */
public class Sort {

    /**
     * 沉底，基于移动
     * @param array 数组
     */
    public static void insertionSort(int[] array) {
        for (int i = 0 ; i < array.length ; i ++) {
            int x = array[i];
            int j;
            for (j = i ; j > 0 && array[j-1] > x ; j --) {
                //挪动位置
                array[j] = array[j-1];
            }
            array[j] = x;
        }
    }

    //基于交换
    public static void bubboSort(int[] array) {
        for (int i = 0 ; i < array.length ; i ++) {
            int j;
            for (j = 0 ; j < array.length - i - 1; j ++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }

    public static int findNum(int[] array) {
        int result = array[0];
        for (int i = 1 ; i < array.length ; i ++) {
            result ^= array[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int array[] = new int[] {5,5,3,3,1};
        int a = findNum(array);
        System.out.println(a);
    }
}
