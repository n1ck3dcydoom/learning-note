package leetcode.bit.easy;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 5
 * 输出：true
 * 解释：5 的二进制表示是：101
 * 示例 2：
 * <p>
 * 输入：n = 7
 * 输出：false
 * 解释：7 的二进制表示是：111.
 * 示例 3：
 * <p>
 * 输入：n = 11
 * 输出：false
 * 解释：11 的二进制表示是：1011.
 * 示例 4：
 * <p>
 * 输入：n = 10
 * 输出：true
 * 解释：10 的二进制表示是：1010.
 * 示例 5：
 * <p>
 * 输入：n = 3
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-number-with-alternating-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/6/22 17:45
 **/
public class _693_BinaryNumberWithAlternatingBits {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(hasAlternatingBits1(n));
    }

    public static boolean hasAlternatingBits(int n) {
        // 任何数和1做与运算后，都能得到这个数的最后一位值
        // 3 & 1 = 011 & 1 = 011 & 001 = 001 最后一位是1
        // 6 & 1 = 110 & 1 = 110 & 001 = 000 最后一位是0
        int lastBit = n & 1;
        // n无符号右移 (去掉最后一位，前面补0)
        n = n >>> 1;
        while (n != 0) {
            int current = n & 1;
            if (lastBit == current) {
                return false;
            }
            lastBit = current;
            n = n >>> 1;
        }
        return true;
    }

    public static boolean hasAlternatingBits1(int n) {
        // 如果一个数满足其二进制 0和1的交替显示
        // 那么把当前数n 错1位后再和原来的数进行异或，结果肯定全部是1
        // 把这个结果加上1，全部进位后得到一个首位为1的新结果

        // 定理 : n&(n-1) 会把n的最后一个1变为0
        // 10&9 = 1010 & 1001 = 1000  这样就把1010中的最后一个1变成了0，即1000

        // 把加1后的结果进行n&(n-1)
        // 如果最后的结果为0，则满足题意

        n = (n ^ (n >>> 1)) + 1;
        return (n & (n - 1)) == 0;
    }
}