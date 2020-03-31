package algorithm.sort;


import java.util.Stack;

/**
 * Created by n!Ck
 * Date: 2019-01-03
 * Time: 12:37
 * Description:
 */
public class QuickSort0 {
    private static int[] numbers = {6, 2, 3, 7, 5, 1, 0, 8, 9, 4, 2};

    public static void main(String[] args) {
        quickSort2(numbers, 0, numbers.length - 1);
        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }

    /**
     * 递归实现快速排序
     *
     * @param numbers 待排序数组
     * @param low     低基准位置
     * @param high    高基准位置
     */
    public static void quickSort(int[] numbers, int low, int high) {
        if (low >= high) {
            return;
        }
        // 得到每趟排序后的划分位置
        int pos = partition(numbers, low, high);
        // 左边递归快排
        quickSort(numbers, low, pos - 1);
        // 右边递归快排
        quickSort(numbers, pos + 1, high);
    }

    /**
     * 循环实现快速排序
     *
     * @param numbers 待排序数组
     * @param low     低基准位置
     * @param high    高基准位置
     */
    private static void quickSort2(int[] numbers, int low, int high) {
        if (low >= high) {
            return;
        }
        Stack<Integer> stack = new Stack<>();
        // 先压入左指针，再压入右指针
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int i = stack.pop();
            int pos;

            // 得到每趟排序后的划分位置
            pos = partition(numbers, i, j);
            // 先压入左指针，再压入右指针
            if (low < pos - 1) {
                stack.push(low);
                stack.push(pos - 1);
            }
            if (pos + 1 < j) {
                stack.push(pos + 1);
                stack.push(j);
            }
        }
    }

    private static int partition(int[] numbers, int low, int high) {
        if (numbers.length == 0 || low > high) {
            return -1;
        }
        int i = low;
        int j = high;
        int pos = numbers[low];
        while (i < j) {
            // 左边为基准，先从从右往左找第一个比基准小的数
            while (i < j && numbers[j] >= pos) {
                j--;
            }
            // 再从左往右找第一个比基准大的数
            while (i < j && numbers[i] <= pos) {
                i++;
            }
            // 交换两个数的位置
            if (i < j) {
                numbers[i] = numbers[i] + numbers[j];
                numbers[j] = numbers[i] - numbers[j];
                numbers[i] = numbers[i] - numbers[j];
            }
        }
        // 交换指针重合位置和基数
        numbers[low] = numbers[i];
        numbers[i] = pos;

        return i;
    }
}
