package leetcode.easy.bit;

/**
 * @author zhanglei
 * @version 1.0
 * @description zl
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 * <p>
 *  
 * <p>
 * 提示：
 * <p>
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 *  
 * <p>
 * 进阶：
 * <p>
 * 如果多次调用这个函数，你将如何优化你的算法？
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 * 示例 2：
 * <p>
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 * 示例 3：
 * <p>
 * 输入：11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 *  
 * <p>
 * 提示：
 * <p>
 * 输入必须是长度为 32 的 二进制串 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-1-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/1/13 22:48
 **/
public class _191_NumberOf1Bits {

    public static void main(String[] args) {
        System.out.println(hammingWeight(-3));
    }

    // you need to treat n as an unsigned value
    public static int hammingWeight(int n) {
        // 汉明重量，一个数的二进制形式有多少个1，则汉明重量为几
        // 3(10) = 011(2)  则3的汉明重量为2

        // 遍历这个输入数组的32位，如果某一位是1，则计数器加1
        int count = 0;
        // 每次判断后，把n右移1位，移动32次后，n==0
        while (n != 0) {
            // 任何数和1做与运算后，都能得到这个数的最后一位值
            // 3 & 1 = 011 & 1 = 011 & 001 = 001 最后一位是1
            // 6 & 1 = 110 & 1 = 110 & 001 = 000 最后一位是0
            if ((n & 1) == 1) {
                count++;
            }
            // >>> 无符号右移，如果是有符号数>>右移一位后，最高位补1，导致n永远不可能等于0
            n = n >>> 1;
        }
        return count;
    }

    public static int hammingWeight1(int n) {
        int count = 0;
        while (n != 0) {
            // n和n-1做与运算后，会把n的最后一个1变为0
            //       n: ... 1  1  0  1  0  0
            //     n-1: ... 1  1  0  0  1  1
            // n&(n-1): ... 1  1  0  0  0  0
            //      -1: ... 1  0  1  1  1  1
            // n&(n-1): ... 1  0  0  0  0  0
            //      -1: ... 0  1  1  1  1  1
            // n&(n-1): ... 0  0  0  0  0  0
            // 在二进制中，n-1实际就是把n的最后一个1减去1，变为0
            // 然后把最后一个1后面的所有0全部借位补位1
            // 这样做位运算之后，n的最后一个1就变成0，它后面的0仍然保持不变
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}