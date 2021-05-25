package leetcode.bit.easy;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * 示例2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/5/25 22:41
 **/
public class _136_SingleNumber {
    public int singleNumber(int[] nums) {
        // 任何额外数据结构都不满足空间复杂度

        // 题干中有提示除了要找的数字意外，其他任何数都出现2次

        // 考虑位运算

        // 异或运算的性质  交换律：a^b^a = a^a^b
        // a^a = 0
        // a^0 = a
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}