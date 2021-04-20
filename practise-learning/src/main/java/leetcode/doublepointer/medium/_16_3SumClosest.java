package leetcode.doublepointer.medium;

import java.util.Arrays;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个包括n 个整数的数组nums和 一个目标值target。找出nums中的三个整数，使得它们的和与target最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 3 <= nums.length <= 10^3
 * -10^3<= nums[i]<= 10^3
 * -10^4<= target<= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/4/20 23:03
 **/
public class _16_3SumClosest {
    public int threeSumClosest(int[] nums, int target) {
        // 首先对数组排序

        // 然后遍历数组i，使用双指针p从i+1向后和q从n-1向前开始
        // 每次更新当前组合是否距离target更近
        // 通过当前组合的和sum和target做差的绝对值，如果小于初始值res和target的差的绝对值
        // 那么更新res为当前sum，否则开始移动双指针

        // 如果当前组合比target更大，则让q--，因为这样会让新的sum变小
        // 如果当前组合比target更小，则让p++，因为这样会让新的sum变大
        // 直到p和q相遇，这样就相当于遍历完了所有数组

        // pq相遇后，i继续遍历整个数组，直到i = n-2  (n-2才能保证后面pq数组初始化不越界)

        // 排序
        Arrays.sort(nums);

        // 定义初始值为第一次遍历的结果
        int n = nums.length;
        int res = nums[0] + nums[n - 1] + nums[n - 2];

        // 开始遍历i
        for (int i = 0; i < n - 2; i++) {
            // 双指针
            int p = i + 1;
            int q = n - 1;
            // 双指针根据上面的策略开始查找
            while (p != q) {
                // 当前组合的和
                int sum = nums[i] + nums[p] + nums[q];
                // 判断当前sum和上次组合res两者谁距离target更近
                res = (Math.abs(sum - target) > Math.abs(res - target)) ? res : sum;

                // 当前组合大于target，q--，让sum变小
                if (nums[i] + nums[p] + nums[q] > target) {
                    q--;
                }
                // 当前组合小于target，p++，让sum变大
                else {
                    p++;
                }
            }
        }
        return res;
    }
}