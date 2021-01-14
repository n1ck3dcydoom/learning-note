package leetcode.dp.medium;

/**
 * @author zhanglei
 * @version 1.0
 * @description leetcode
 * 把字符串 s 看作是“abcdefghijklmnopqrstuvwxyz”的无限环绕字符串，所以 s 看起来是这样的："...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....". 
 * <p>
 * 现在我们有了另一个字符串 p 。你需要的是找出 s 中有多少个唯一的 p 的非空子串，尤其是当你的输入是字符串 p ，你需要输出字符串 s 中 p 的不同的非空子串的数目。 
 * <p>
 * 注意: p 仅由小写的英文字母组成，p 的大小可能超过 10000。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: "a"
 * 输出: 1
 * 解释: 字符串 S 中只有一个"a"子字符。
 *  
 * <p>
 * 示例 2:
 * <p>
 * 输入: "cac"
 * 输出: 2
 * 解释: 字符串 S 中的字符串“cac”只有两个子串“a”、“c”。.
 *  
 * <p>
 * 示例 3:
 * <p>
 * 输入: "zab"
 * 输出: 6
 * 解释: 在字符串 S 中有六个子串“z”、“a”、“b”、“za”、“ab”、“zab”。.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-substrings-in-wraparound-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/1/14 23:15
 **/
public class _467_UniqueSubstringsInWraparoundString {
    /**
     * 题意：
     * p的子串同时也是s的子串
     * p的子串必须是连续的 注意：z和a是连续的
     * <p>
     * 即求p中每个字母结尾的所有连续子串的个数和
     */
    public int findSubstringInWraproundString(String p) {
        // 有点前缀和的意思

        // 对于连续字符串 abcdef 来说
        // 以a结尾的连续子串有 'a' 1个                                        a -> 1
        // 以b结尾的连续子串有 'b' 'ab' 2个                                  ab -> 3
        // 以c结尾的连续子串有 'c' 'bc' 'abc' 3个                           abc -> 6
        // 以d结尾的连续子串有 'd' 'cd' 'bcd' 'abcd' 4个                   abcd -> 10
        // 以e结尾的连续子串有 'e' 'de' 'cde' 'bcde' 'abcde' 5个          abcde -> 15
        // 以f结尾的连续子串有 'f' 'df' 'edf' 'cdef' 'bcdef' 'abcdef'6个 abcdef -> 21
        // 那么对于连续字符串 abcdef 来说，他的所有不重复的连续字符子串就有
        // 以a结尾，以b结尾... 以f结尾的连续子串之和 1+2+3+4+5+6 = 21g个

        // 0 <= i <= 25
        // 第一步、定义dp[i] 表示以字母i(26字母表中顺序)结尾的连续子串和
        int[] dp = new int[26];

        // 第二步、初始值
        // 只有字母a的时候，连续子串和为1
        dp[0] = 1;

        // 第三步、选择，递推表达式
        // 如果dp[i]与前一个dp[i-1]连续，那么dp[i] = dp[i-1] + (pos[i] + 1)
        // 例如，abcde，以字母e结尾的连续子串和dp[4] = dp[3] + (pos[e] + 1)
        //                                  dp[4] = dp[3] + 4 + 1 = 15

        // 如果dp[i]与前一个dp[i-1]不连续，那么dp[i] = dp[i-1] + 1
        // 前面连续的i-1个子串和 + 第i个不连续的字符本身
        // abcdx 以x结尾的连续子串和 dp[4] = dp[3] + 1
        // dp[3] = 10 但是x和d不连续，所以只能是 a,b,ab,c,bc,abc,d,cd,bcd,abcd 这10个
        // 加上x本身 即dp[4] = dp[3] + 1 = 11

        // 这里是有问题的，如果字符串 zabcdabc
        // 其中zabc abc  后面的abc已经被zabc包含了，在计算c结尾的子串中，后面的abc是重复的
        // 同理 a, b结尾的子串，也是重复的
        return -1;
    }
}