package leetcode.easy;

import java.util.Scanner;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 
 *              说明: 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 *              你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。 示例: 输入: nums1
 *              = [1,2,3,0,0,0], m = 3 nums2 = [2,5,6], n = 3 输出: [1,2,2,3,5,6]
 * 
 *              来源：力扣（LeetCode）
 *              链接：https://leetcode-cn.com/problems/merge-sorted-array
 *              著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/4/16 上午12:08
 **/

public class _88_MergeSortedArray {

    public static void main(String[] args) {

        // int[] nums1;
        // int[] nums2;
        // int m;
        // int n;

        // try (Scanner scanner = new Scanner(System.in)) {

        // m = scanner.nextInt();
        // n = scanner.nextInt();

        // nums1 = new int[m + n];
        // nums2 = new int[n];

        // for (int i = 0; i < m; i++) {
        // nums1[i] = scanner.nextInt();
        // }
        // for (int i = 0; i < n; i++) {
        // nums2[i] = scanner.nextInt();
        // }
        // }

        int[] nums1 = new int[] { 1, 3, 7, 8, 0, 0 };
        int[] nums2 = new int[] { 2, 6 };

        merge(nums1, 4, nums2, 2);

        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + " ");
        }
    }

    private static void merge(int[] nums1, int m, int[] nums2, int n) {
        // 双指针遍历
        int p = 0;
        int q = 0;
        // temp数组保存nums1
        int[] temp = new int[m];
        for (int i = 0; i < m; i++) {
            temp[i] = nums1[i];
        }
        // nums1数组当作结果返回
        int i = 0;
        while (p < m || q < n) {
            if (p == m) {
                nums1[i++] = nums2[q++];
            } else if (q == n) {
                nums1[i++] = temp[p++];
            } else {
                nums1[i++] = temp[p] < nums2[q] ? temp[p++] : nums2[q++];
            }
        }
    }
}