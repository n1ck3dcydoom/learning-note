package leetcode.dp.hard;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定三个字符串s1、s2、s3，请你帮忙验证s3是否是由s1和s2 交错 组成的。
 * <p>
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 * <p>
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 * <p>
 * 提示：
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1、s2、和 s3 都由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interleaving-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/3/8 22:32
 **/
public class _97_InterleavingString {

    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        System.out.println(isInterleave(s1, s2, "aadbbcbcac"));
    }

    public static boolean isInterleave(String s1, String s2, String s3) {

        // 第一眼一看，两个字符串s1和s2，能否交错构成长度为len(s1) + len(s2) 的字符串s3
        // 第一反应就是需要二维dp[i][j] 表示在s1中以i结尾，s2中以j结尾的字符串，能否 "交错" 构成长度为i+j的字符串s3
        // 因为题目求的是能否构成，所以dp的含义自然也就是boolean类型

        // 假设s1当作纵轴，s2当作横轴，表格画出来，感觉就是填表格
        // 通常填表格，几乎dp数组都多定义了一个空间，用来表示s1和s2都是空串的情况下的初始值
        int len1 = s1.length();
        int len2 = s2.length();

        // TODO 通过下面的分析，这里需要过滤掉最基本的异常情况
        int len3 = s3.length();
        if (len1 + len2 != len3) {
            return false;
        }

        boolean[][] dp = new boolean[len1 + 1][len2 + 1];

        // 二维dp的初始化，除了dp[0][0]这个位置以外，通常还需要初始化第一行和第一列
        // 显然s1和s2都是空串的情况下，一定能够构成同样是空串的s3
        dp[0][0] = true;
        // 初始化第一列，即s2是空串的情况下，由s1单独构成s3
        // dp[i][0]依赖于dp[i-1][0]的结果，因为只有前面的所有项都能构成s3时，在判断i能否构成s3的最后一项
        for (int i = 1; i <= len1; i++) {
            // 这里观察题干，发现s3也有可能等于0，而且题干，没有给出s1、s2和s3的长度关系
            // 这促使我们思考，这种情况下，怎样才能保证s1和s2一定能够构成满足条件的s3呢
            // 从长度关系来说，s3由s1和s2拼接而成，那么s3的长度一定等于s1加上s2的长度
            // TODO 所以倒过来需要在最前面判断异常情况
            // TODO 因为走到这里默认是s1和s2已经达到最基本构成s3的条件，至于是否能够最终构成，需要通过计算dp得到

            // 这里因为i从1开始遍历到len1，所以第i个元素其实对应s1中的下标i-1，同理对应s3的i-1
            dp[i][0] = dp[i - 1][0] && s3.charAt(i - 1) == s1.charAt(i - 1);
        }
        // 初始化第一行，即s1是空串的情况下，由s2单独构成s3
        for (int i = 1; i <= len2; i++) {
            dp[0][i] = dp[0][i - 1] && s3.charAt(i - 1) == s2.charAt(i - 1);
        }

        // 二维dp填表很简单，先遍历一个轴，再遍历另外一个轴
        // 假设先遍历行，再遍历列
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 寻找递推表达式
                // 思考对于dp[i][j]来说，怎么通过前面的子问题得到当前问题的结果
                // 对于dp[i][j]来说，如果当前面一个字符是来自于s1串
                // 那么只需要判断前面以s1结尾的dp[i-1][j]的boolean值，和s1当前以i结尾的字符s1(i)和是否等于s3以(i-1)+j的字符s3(i+j-1)是否相等

                // 同理，如果前面一个字符来自于s2串
                // 那么只需要判断前面以s2串结尾的dp[i][j-1]的boolean值，和s2当前以j结尾的字符s2(j)是否等于s3以i+(j-1)的字符s3(i+j-1)是否相等
                // 这样突然发现，和以前的求从起点到终点的不同路径的动态规划题目非常相似

                // 综上所述：递推表达式
                // dp[i][j] = (dp[i-1][j] && s1[i-1]==s3[i+j-1]) || (dp[i][j-1] && s2[j-1]==s3[i+j-1])

                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2
                        .charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[len1][len2];
    }
}