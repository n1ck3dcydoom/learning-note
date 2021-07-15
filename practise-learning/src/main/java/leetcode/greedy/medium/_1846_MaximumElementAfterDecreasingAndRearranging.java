package leetcode.greedy.medium;

import java.util.Arrays;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个正整数数组 arr 。请你对 arr 执行一些操作（也可以不进行任何操作），使得数组满足以下条件：
 * <p>
 * arr 中 第一个 元素必须为 1 。
 * 任意相邻两个元素的差的绝对值 小于等于 1 ，也就是说，对于任意的 1 <= i < arr.length （数组下标从 0 开始），都满足 abs(arr[i] - arr[i - 1]) <= 1 。abs(x) 为 x 的绝对值。
 * 你可以执行以下 2 种操作任意次：
 * <p>
 * 减小 arr 中任意元素的值，使其变为一个 更小的正整数 。
 * 重新排列 arr 中的元素，你可以以任意顺序重新排列。
 * 请你返回执行以上操作后，在满足前文所述的条件下，arr 中可能的 最大值 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [2,2,1,2,1]
 * 输出：2
 * 解释：
 * 我们可以重新排列 arr 得到 [1,2,2,2,1] ，该数组满足所有条件。
 * arr 中最大元素为 2 。
 * 示例 2：
 * <p>
 * 输入：arr = [100,1,1000]
 * 输出：3
 * 解释：
 * 一个可行的方案如下：
 * 1. 重新排列 arr 得到 [1,100,1000] 。
 * 2. 将第二个元素减小为 2 。
 * 3. 将第三个元素减小为 3 。
 * 现在 arr = [1,2,3] ，满足所有条件。
 * arr 中最大元素为 3 。
 * 示例 3：
 * <p>
 * 输入：arr = [1,2,3,4,5]
 * 输出：5
 * 解释：数组已经满足所有条件，最大元素为 5 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 105
 * 1 <= arr[i] <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-element-after-decreasing-and-rearranging
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/15 19:59
 **/
public class _1846_MaximumElementAfterDecreasingAndRearranging {

    public static void main(String[] args) {
        int[] arr = new int[]{100, 1, 1000};
        System.out.println(maximumElementAfterDecrementingAndRearranging(arr));
    }

    public static int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        // 排序
        // quickSort(arr, 0, n - 1);
        Arrays.sort(arr);
        // 调整首位
        if (arr[0] != 1) {
            arr[0] = 1;
        }
        for (int i = 1; i < n; i++) {
            if (Math.abs(arr[i] - arr[i - 1]) > 1) {
                arr[i] = arr[i - 1] + 1;
            }
        }
        return arr[n - 1];
    }

    /**
     * 快排
     */
    private static void quickSort(int[] originArray, int l, int r) {
        if (l > r) {
            return;
        }
        // 哨兵
        int temp = originArray[l];

        int i = l, j = r;
        while (i < j) {
            // 先从右边开始遍历，找到第一个小于哨兵的数
            while (originArray[j] >= temp && i < j) {
                j--;
            }
            // 再从左边开始遍历，找到第一个大于哨兵的数
            while (originArray[i] <= temp && i < j) {
                i++;
            }
            if (i < j) {
                // 交换
                originArray[j] = originArray[i] + originArray[j];
                originArray[i] = originArray[j] - originArray[i];
                originArray[j] = originArray[j] - originArray[i];
            }
        }
        // 哨兵交换
        originArray[l] = originArray[i];
        originArray[i] = temp;

        quickSort(originArray, l, i - 1);
        quickSort(originArray, j + 1, r);

    }
}