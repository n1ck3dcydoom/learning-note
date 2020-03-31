package algorithm.sort;

import java.util.Arrays;

/**
 * Created by n!Ck Date: 2018/10/8 Time: 22:35 Description: 快速排序
 */
public class QuickSort1 {
    public static Integer[] numbers = { 6, 1, 2, 7, 9, 3, 4, 5, 10, 8 };

    public static void main(String[] args) {
        quickSort(0, numbers.length - 1);
        Arrays.asList(numbers).forEach(e -> System.out.print(e + " "));
    }

    public static void quickSort(int left, int right) {
        if (left > right) {
            return;
        }
        // 监视岗选为左边第一个
        int flag = numbers[left];
        int temp;
        int i = left;
        int j = right;
        while (i != j) {
            while (numbers[j] >= flag && j > i) {
                --j;
            }
            while (numbers[i] <= flag && j > i) {
                ++i;
            }
            // 交换右边比监视哨小的数
            // 交换左边比监视哨大的数
            if (j > i) {
                temp = numbers[j];
                numbers[j] = numbers[i];
                numbers[i] = temp;
            }
        }
        // 当左右相等时，交换监视哨
        temp = numbers[i];
        numbers[i] = numbers[left];
        numbers[left] = temp;
        // 递归实现快速排序
        quickSort(left, i - 1);
        quickSort(i + 1, right);
    }
}
