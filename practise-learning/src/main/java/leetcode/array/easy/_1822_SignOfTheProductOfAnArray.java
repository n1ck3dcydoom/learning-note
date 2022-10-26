package leetcode.array.easy;

/**
 * Created by n!Ck
 * Date: 2022/10/27
 * Time: 00:02
 * Description:
 */

public class _1822_SignOfTheProductOfAnArray {

    public int arraySign(int[] nums) {
        // 如果数组中含有 0,则乘积一定为 0

        // 如果不含有 0,则需要判断所有元素的乘积的符号
        // 不用实际去相乘,肯定存在溢出的用例
        // 由于乘积的符号只依赖于所有乘数当中负数的个数
        // 若有偶数个负数,则最终乘积为正数
        // 若有奇数个负数,则最终乘积为负数

        int k = 0;
        for (int num : nums) {
            // 出现 0 就直接返回
            if (num == 0) {
                return 0;
            } else {
                // 统计负数的个数
                k += num > 0 ? 0 : 1;
            }
        }
        return k % 2 == 0 ? 1 : -1;
    }
}
