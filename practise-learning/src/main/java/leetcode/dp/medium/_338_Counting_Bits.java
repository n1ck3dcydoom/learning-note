package leetcode.dp.medium;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description zl
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 * <p>
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/1/13 23:19
 **/
public class _338_Counting_Bits {
    // 根据题目191 位1的个数，写一个求数字n的位1个数的方法，然后遍历0~num即可
    // 时间复杂度O(n*k) k = sizeof(num) 即k等于num所占的位数
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            res[i] = this.bit1count(i);
        }
        return res;
    }

    private int bit1count(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    /**
     * 动态规划O(n)
     * 奇数的二进制末尾一定是1，偶数的二进制末尾一定是0
     * <p>
     * 对于奇数，二进制表示中，一定比前一个偶数要多一个1，前一个偶数+1后就成了奇数的末位的1
     * 0 = 0   ,  1 = 1 = 0 + 1 = 1
     * 2 = 10  ,  3 = 11 = 10 + 1 = 11
     * 6 = 0110 , 7 = 111 = 0110 + 1 = 0111
     * <p>
     * 对于偶数，二进制表示中，位1的个数等于其除以2之后那个数的位1个数
     * 2 = 10  ,  4 = 100  ,  8 = 1000
     * 3 = 11  ,  6 = 110  , 12 = 1100
     * 偶数除以2，相当于无符号右移1位，丢掉末尾的0
     */
    public int[] countBits0(int num) {
        // 第一步、定义dp数组
        // dp[i]表示数字i的位1的个数
        int[] dp = new int[num + 1];

        // 第二步、初始化
        // 0的位1个数等于0
        dp[0] = 0;

        // 第三步、选择，即递推表达式
        // dp[i] 两个选择：1、i是奇数  2、i是偶数
        // 若i是奇数，则i的位1个数等于前一个偶数(即前一个数)的位1个数+1
        // dp[i] = dp[i-1] +1
        //
        // 若i是偶数，则i的位1个数等于i除以2后的数的位1个数
        // dp[i] = dp[i/2]
        for (int i = 1; i <= num; i++) {
            // 奇数
            if (i % 2 != 0) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = dp[i / 2];
            }
        }
        return dp;
    }

    public int[] countBits1(int num) {
        // 定义dp[i]表示i的位1个数
        // 观察任意一个数             605 = 1001011101
        // 如果把它无符号右移1位后得到 302 = 0100101110
        // 可以发现605和302除了最后一位不同以外，前面的所有位数都相同
        // 可以得到递推关系式：dp[i] = dp[i/2] + i的最后一位是不是1

        // 如何求得i的最后一位是不是1
        // 参考题目191，一个数和1做与运算后，可以得到其最后一位

        // 综上所述：递推关系式 dp[i] = dp[i/2] + i&1

        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 1; i <= num; i++) {
            // dp[i] = dp[i / 2] + (i & 1); // 注意运算符优先级+的优先级在&的前面，i&1需要括号
            dp[i] = dp[i >>> 2] + (i & 1);
        }
        return dp;
    }
}