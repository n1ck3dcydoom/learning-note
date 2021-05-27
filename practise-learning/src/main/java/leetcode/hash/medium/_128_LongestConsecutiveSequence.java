package leetcode.hash.medium;

import java.util.HashSet;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 *
 * <p>
 * 进阶：你可以设计并实现时间复杂度为O(n) 的解决方案吗？
 * <p>
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * <p>
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/5/27 22:13
 **/
public class _128_LongestConsecutiveSequence {

    public static void main(String[] args) {
        int[] nums = new int[]{100, 4, 200, 1, 2, 3};
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 使用hashSet存储nums数组，可以去重，也可以在O(1)时间范围内完成查询操作
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // 保存最终的最长连续长度
        int res = 1;

        // 遍历数组，如果某个数x是最长连续子序列的开头，那么x-1一定不在集合当中
        // 所以每次遍历x，先判断x-1在不在集合
        // 在的话跳过，不在的话就加入当前遍历的最长长度
        // 如果这个数
        for (int num : set) {
            // x-1不在集合当中
            if (!set.contains(num - 1)) {
                // 保存当前遍历的最长连续长度
                int currentRes = 1;
                // 开始访问x+1，x+2 .... 等等是否在集合当中
                while (set.contains(num + 1)) {
                    currentRes++;
                    num++;
                }
                // 更新最大值
                res = Math.max(res, currentRes);
            }
        }
        return res;
    }
}