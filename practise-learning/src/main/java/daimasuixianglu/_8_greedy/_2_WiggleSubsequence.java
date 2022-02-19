package daimasuixianglu._8_greedy;

/**
 * @author n!Ck
 * @version 1.0
 * @description TODO
 * @date 2022/2/18 20:34
 **/

public class _2_WiggleSubsequence {

    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        // 前一个差值pre和当前差值的乘积为负数，则说明异号是一个摆动波段

        // 第1个和第0个数构成第一个差值
        int pre = nums[1] - nums[0];
        // 当前计算的差值
        int cur = 0;
        // 统计子序列长度
        int count = pre == 0 ? 1 : 2;
        // 遍历数组，从第三个数开始
        for (int i = 2; i < nums.length; i++) {
            cur = nums[i] - nums[i - 1];

        }


        return -1;
    }
}
