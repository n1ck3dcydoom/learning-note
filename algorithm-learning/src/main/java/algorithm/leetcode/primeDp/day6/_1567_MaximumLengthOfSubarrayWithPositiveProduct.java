package algorithm.leetcode.primeDp.day6;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/9/27 0:55
 **/
public class _1567_MaximumLengthOfSubarrayWithPositiveProduct {
    public int getMaxLen(int[] nums) {

        int n = nums.length;
        if (n == 1) {
            return nums[0] > 0 ? 1 : 0;
        }
        // 类似乘积最大的定义，需要两个数组
        // 第一步、定义positive数组
        // 定义positive[n]表示以nums[n]结尾的乘积为正的最长子数组长度
        // 定义negative[n]表示以nums[n]结尾的乘积为负的最长子数组长度
        int[] positive = new int[n + 1];
        int[] negative = new int[n + 1];

        // 第二步、初始值
        positive[0] = 0;
        positive[1] = nums[0] > 0 ? 1 : 0;
        negative[0] = 0;
        negative[1] = nums[0] >= 0 ? 0 : 1;

        // 第三步、递推表达式
        // 考虑以nums[i]结尾的positive[i]、negative[i]
        // 若nums[i]>0，正数不改变符号
        // positive[i]=positive[i-1]+1
        // 若negative[i-1]>0，负数乘以正数不改变符号，negative[i]=negative[i-1]+1
        // 若negative[i-1]=0，0不能当做负数，negative[i]=0

        // 若nums[i]<0，负数会改变符号
        // negative[i] = positive[i-1]+1，正数乘以负数得到负数
        // positive[i]由负数子数组决定
        // 若negative[i-1]>0，positive[i]=negative[i-1]+1，负负得正
        // 若negative[i-1]=0，0不能够当做正数，positive[i]=0

        // 若nums[i]==0，则以nums[i]结尾的乘积都为0
        // positive[i]=negative[i]=0

        // 此处可以优化空间，i的状态只与i-1相关
        int max = nums[0] > 0 ? 1 : 0;
        int min = nums[0] >= 0 ? 0 : 1;
        // 初始值的答案和max相关，即第一个数num[0]究竟是正数还是负数
        // nums[0]为正数，则初始结果长度为1，否则长度为0
        int res = max;
        for (int i = 2; i <= n; i++) {
            if (nums[i - 1] > 0) {
                max = max + 1;
                min = min > 0 ? min + 1 : 0;
            } else if (nums[i - 1] < 0) {
                int temp = min;
                min = max + 1;
                // 这里max的计算依赖min，上面会更新min的值，需要临时保存min
                max = temp > 0 ? temp + 1 : 0;
            } else {
                max = 0;
                min = 0;
            }
            res = Math.max(res, max);
        }
        return res;
    }
}