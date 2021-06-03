package leetcode.presum.medium;

import java.util.HashMap;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 * 示例 2:
 * <p>
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 *  
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contiguous-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/6/3 18:49
 **/
public class _525_ContiguousArray {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1};
        System.out.println(findMaxLength1(nums));
    }

    public int findMaxLength(int[] nums) {

        int n = nums.length;
        // 定义前缀和数组
        // 直接原地置换
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }
        int res = 0;
        // 遍历前缀和数组，如果当前前缀和等于区间长度的一半，则说明找到一个具有相同数量的0和1的连续子数组
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum;
                if (i == 0) {
                    sum = nums[j];
                } else {
                    sum = nums[j] - nums[i - 1];
                }
                // 数组长度为奇数的，肯定不满足
                if (((j - i + 1) % 2 == 0) && (j - i + 1) / 2 == sum) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
        // 果然超时了。。。
    }

    public static int findMaxLength1(int[] nums) {
        // 参考523题，如何使用hash表优化时间复杂度
        // 转化一下0和1，求相同个0和1的连续子数组
        // 如果遇到0就让和-1，遇到1就让和+1
        // 即原题中的0和1相同个数的连续子数组就转化为，在[i,j]的区间内，和为0

        // 考虑位置n的前缀和为x，如果在后面若干个元素后有位置m的前缀和也为x
        // 可以得出在区间[n,m]内的和为0，即有相同个0和1的元素出现

        // 通过hash表存储第一次出现某个前缀和的下标，如果在后面遍历的时候
        // 第二次出现了相同下标，求出两次位置之间的长度，每次更新最大值即为最终答案

        int n = nums.length;
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        // 考虑特殊情况，如果只有两个数0和1
        // map里面会存放-1,0
        // 第二次进来的sum=0，i=1发现map里面有(-1,0)
        // 此时会找不到sum=0的key，所以输出错误

        // 放入哨兵(0,-1)
        map.put(0,-1);

        // 遍历nums数组，求每次遍历到当前位置的前缀和
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                sum += 1;
            } else {
                sum -= 1;
            }
            // 记录当前位置的前缀和
            if (map.containsKey(sum)) {
                // 如果之前已经出现了前缀和，就更新最大子数组的长度
                res = Math.max(res, i - map.get(sum));
            } else {
                // 没出现过前缀和就放入哈希表中
                map.put(sum, i);
            }
        }
        return res;
    }
}