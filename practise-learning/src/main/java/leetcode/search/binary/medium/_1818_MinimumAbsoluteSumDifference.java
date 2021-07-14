package leetcode.search.binary.medium;

import java.util.Arrays;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。
 * <p>
 * 数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始）。
 * <p>
 * 你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。
 * <p>
 * 在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 109 + 7 取余 后返回。
 * <p>
 * |x| 定义为：
 * <p>
 * 如果 x >= 0 ，值为 x ，或者
 * 如果 x <= 0 ，值为 -x
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,7,5], nums2 = [2,3,5]
 * 输出：3
 * 解释：有两种可能的最优方案：
 * - 将第二个元素替换为第一个元素：[1,7,5] => [1,1,5] ，或者
 * - 将第二个元素替换为第三个元素：[1,7,5] => [1,5,5]
 * 两种方案的绝对差值和都是 |1-2| + (|1-3| 或者 |5-3|) + |5-5| = 3
 * 示例 2：
 * <p>
 * 输入：nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
 * 输出：0
 * 解释：nums1 和 nums2 相等，所以不用替换元素。绝对差值和为 0
 * 示例 3：
 * <p>
 * 输入：nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
 * 输出：20
 * 解释：将第一个元素替换为第二个元素：[1,10,4,4,2,7] => [10,10,4,4,2,7]
 * 绝对差值和为 |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == nums1.length
 * n == nums2.length
 * 1 <= n <= 105
 * 1 <= nums1[i], nums2[i] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-absolute-sum-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/14 21:37
 **/
public class _1818_MinimumAbsoluteSumDifference {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int mod = 1000000007;
        int n = nums1.length;

        // 外层遍历数组用于求和的绝对值
        int sumAbs = 0;
        // 替换i后能够得到的最大差值的绝对值
        int diffAbs = 0;
        // 外层遍历数组的绝对值之和
        long sum = 0;

        // 对nums1拷贝后排序
        int[] nums3 = Arrays.copyOf(nums1, n);
        Arrays.sort(nums3);

        for (int i = 0; i < n; i++) {
            // 原始绝对值差值
            sumAbs = Math.abs(nums1[i] - nums2[i]);
            sum += sumAbs;
            // 内层遍历数组用于找到i前后能够得到的最小绝对值
            // 暴力遍历超时，使用二分在nums1中查找最接近nums2[i]的绝对值差值
            // 如果nums1中存在一个新的绝对值差值minAbs满足minAbs<sumAbs
            // 就更新diffAbs = sumAbs - minAbs
            // 每次就更新diffAbs，找到最大的diffAbs
            int minAbs = Integer.MAX_VALUE;

            // 这里的l是大于等于nums2[i]的最左侧端点
            // 例：寻找 target = 5
            // nums[l] = 4 , nums[l+1] = 8
            // 需要检查前一个是的绝对值更小
            int l = binarySearch(nums2[i], nums3);

            if (l == n) {
                minAbs = Math.min(minAbs, Math.abs(nums3[l - 1] - nums2[i]));
            } else {
                minAbs = Math.min(minAbs, Math.abs(nums3[l] - nums2[i]));
            }

            if (l - 1 >= 0) {
                minAbs = Math.min(minAbs, Math.abs(nums3[l - 1] - nums2[i]));
            }
            // 更新更大的diffAbs
            diffAbs = Math.max(diffAbs, sumAbs - minAbs);
        }

        return (int) ((sum - diffAbs) % mod);
    }

    private int binarySearch(int target, int[] nums) {
        int l = 0;
        // r = nums.length搜寻区间是 [l, r) 左闭右开
        int r = nums.length;
        // 循环条件是 l < r ，结束时 l == r，结束时的搜索区间是 [l,l) ，搜索区间为空
        while (l < r) {
            int mid = l + (r - l) / 2;

            // 即使等于target，也要缩小右边界范围
            if (nums[mid] == target) {
                // [l, r) 让r=mid，mid已经搜索过了
                r = mid;
            } else if (nums[mid] < target) {
                // [l, r) l=mid+1，mid已经搜索过了
                l = mid + 1;
            } else if (nums[mid] > target) {
                // [l, r) 让r=mid，mid已经搜索过了
                r = mid;
            }
        }
        // l可能等于n
        return l;
    }
}