package algorithm.sort;

import java.util.Arrays;

/**
 * Created by n!Ck
 * Date: 2019-03-05
 * Time: 14:20
 * Description:
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] num = new int[]{13, 49, 29, 6, 84, 38, 51, 72, 62, 90, 2};
        bubbleSort(num);
        Arrays.stream(num).forEach(e -> System.out.print(e + " "));
    }

    private static void bubbleSort(int[] num) {
        int n = num.length;
        boolean flag = false;
        // 冒泡只需要排序n-1趟
        for (int i = 0; i < n - 1; i++) {
            flag = false;
            // j只需要比较到n-i之前就行了，因为每一趟排序后都会有一个数在正确的位置上
            for (int j = 0; j < n - i - 1; j++) {
                if (num[j] > num[j + 1]) {
                    int temp = num[j];
                    num[j] = num[j + 1];
                    num[j + 1] = temp;
                    flag = true;
                }
            }
            // 如果这一趟排序没有交换元素，则退出排序（说明已经排好序了）
            if (!flag) {
                break;
            }
        }
    }
}
