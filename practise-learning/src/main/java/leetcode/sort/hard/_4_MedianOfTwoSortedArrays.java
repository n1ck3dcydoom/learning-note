package leetcode.sort.hard;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 * <p>
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 * <p>
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 * <p>
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 * <p>
 * <p>
 * 提示：
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 * <p>
 * <p>
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/4/12 23:20
 **/
public class _4_MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // 两个数组已经是有序的了，直接归并排序

        int n1 = nums1.length;
        int n2 = nums2.length;

        // 判断一下边界值
        if (n1 == 0) {
            if (n2 % 2 == 0) {
                return (nums2[(n2 / 2) - 1] + nums2[n2 / 2]) / 2.0;
            } else {
                return nums2[n2 / 2];
            }
        }
        if (n2 == 0) {
            if (n1 % 2 == 0) {
                return (nums1[(n1 / 2) - 1] + nums1[n1 / 2]) / 2.0;
            } else {
                return nums1[n1 / 2];
            }
        }

        int count = 0;
        int i = 0;
        int j = 0;
        // 合并两个数组
        int[] total = new int[n1 + n2];
        while (count < (n1 + n2)) {
            // 当某一个数组已经全部合并完成后，另一个数组剩下的数直接放到新数组尾端

            // 当nums1已经全部合并之后
            if (i == n1) {
                // nums2剩下的全部接到total后面
                while (j != n2) {
                    total[count++] = nums2[j++];
                }
                // 合并完成后直接推出归并
                break;
            }
            // 当nums2已经全部合并之后
            if (j == n2) {
                while (i != n1) {
                    total[count++] = nums1[i++];
                }
                // 合并完成后直接推出归并
                break;
            }
            // 两个数组都没有合并完成，比较当前大小后归并合并
            if (nums1[i] < nums2[j]) {
                total[count++] = nums1[i++];
            } else {
                total[count++] = nums2[j++];
            }
        }

        if (count % 2 == 0) {
            return (total[(count / 2) - 1] + total[count / 2]) / 2.0;
        } else {
            return total[count / 2];
        }
    }
}