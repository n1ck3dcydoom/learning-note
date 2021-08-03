package leetcode.doublepointer.medium;

import java.util.Arrays;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * <p>
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 *  
 * <p>
 * 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/8/3 17:45
 **/
public class _581 {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 6, 4, 8, 10, 9, 15};
        System.out.println(findUnsortedSubarray(nums));
        System.out.println(findUnsortedSubarray1(nums));
    }

    public static int findUnsortedSubarray(int[] nums) {
        // 首先想到的就是，先copy一份，然后完成排序
        // 然后从头到尾和从尾到头对比每个元素，找到左边第一个不相等i，右边第一个不相等的j
        // 计算[i,j]的区间长度，如果i和j相遇了，则说明nums已经是有序的了

        // 对其中某个连续子区间排序后，整个区间成为有序的
        // 即 [0, i) [i, j] (j, n]，对[i,j]排序后，[0,n]整体成为有序

        int n = nums.length;
        int[] copy = Arrays.copyOf(nums, n);
        Arrays.sort(copy);

        // 使用两个指针扫描一遍nums，寻找每一个不同的区间，记录区间的最小值
        int i = 0, j = n - 1;
        // 双指针分别从头到尾扫描nums
        while (i <= j && copy[i] == nums[i]) {
            i++;
        }
        while (i <= j && copy[j] == nums[j]) {
            j--;
        }
        return j - i + 1;
    }

    private static int findUnsortedSubarray1(int[] nums) {
        int n = nums.length;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        //        int l = 0, r = n - 1;
        int l = -1, r = -1;
        for (int i = 0; i < n; i++) {
            // r从左往右扫描，记录最大值，如果出现了比扫描路径最大值更小的数，则移动r
            if (nums[i] < max) {
                r = i;
            } else {
                max = nums[i];
            }

            // l
            // l从右往左扫描，记录最小值，如果出现了比扫描路径最小值更大的数，则移动l
            if (nums[n - i - 1] > min) {
                l = n - i - 1;
            } else {
                min = nums[n - i - 1];
            }
        }
        // 特殊处理下nums已经排序的情况，此时l和r都不变
        //        return l == 0 && r == n - 1 ? 0 : r - l + 1;
        // 原先l初始化为0，r初始化为n-1
        // 如果nums是倒序排序的，此时l=0，r=n-1是符合题意的答案
        // 如果nums是正序排序的，观察到for循环里面是不会更改l和r的值的，所以需要修改l和r的初始默认值
        return l == -1 && r == -1 ? 0 : r - l + 1;
    }
}