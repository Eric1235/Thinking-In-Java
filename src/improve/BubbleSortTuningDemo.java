package improve;

import java.util.Random;

/**
 * Created by EricLi on 2018/1/14.
 * Email me : EricLi1235@gmail.com.
 */
public class BubbleSortTuningDemo {

    public static void main(String[] args) {
        int[] arr = constructDataArray(15);
        printArray(arr);
        bubbleSort4(arr);
        printArray(arr);
    }

    public static int[] constructDataArray(int length) {
        int []arr = new int[length];
        Random random = new Random();
        for (int i = 0 ; i < length ; i ++) {
            arr[i] = random.nextInt(length);
        }
        return arr;
    }

    //把最大的一个放到后面
    public static int[] bubbleSort4(int[] arr) {
        boolean flag = true;
        int n = arr.length;
        while (flag) {
            flag = false;
            for (int j = 0 ; j < n - 1 ; j ++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            n --;
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int d : arr) {
            System.out.print(d + " ");
        }
        System.out.println();
    }

}
