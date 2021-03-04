package leetcode.dp.hard;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 * <p>
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/3/4 19:42
 **/
public class _72_EditDistance {

    public static void main(String[] args) {
        String word1 = "";
        String word2 = "a";
        System.out.println(minDistance(word1, word2));
    }

    public static int minDistance(String word1, String word2) {
        // 朴素dp思路

        // 考虑到有两个字符串/数组/集合，通常都需要依次遍历，尝试用二维dp[i][j]
        // 思考i和j分别表示什么，如果i表示word1的字符下标，j表示word2的字符下标
        // 那么dp[i][j]表示什么？
        // 如果求是否满足条件dp通常存储boolean类型，如果求满足条件的个数dp通常存储int类型

        // 这里需要求最小步骤，所以dp[i][j]表示在word1从0到i，变化到word2的0到j，所需要的最少步骤次数
        // 那么最终答案就是当i等于word1的长度，j等于word2的长度时的dp值

        int n = word1.length();
        int m = word2.length();
        // dp数组在大多数情况下都需要额考虑要不要定义的长度超过word1和word2的长度
        // 思考一下dp[0][0]表示什么？
        // dp[0][0]表示word1前0个字符变换到word2的前0个字符所需要的最小步骤数
        // 题目中是允许word1或者word2的长度等于0的，所以需要从dp数组中留出下标为0的位置
        // 然后dp后面的位置顺序存放word1和word2的第1到第i和第j个元素 (注意word1和word2需要下标+1)
        int[][] dp = new int[n + 1][m + 1];

        // 初始值
        dp[0][0] = 0; // 原串和目标串都没有字符，当然一步都不需要即可完成变换

        // 二维dp的初始值，按照经验来说，不仅仅是初始化dp[0][0]这一个位置
        // 而是需要初始化第一行和第一列的所有元素
        // 试着看第一行和第一列是否能够初始化 (如果需要递推表达式才能计算则表示不能初始化)

        // 这里把word1竖着放，word2横着放

        // 初始化第一列，即word2在没有字符的情况下，word1全部转换为空串所需要的最少步骤
        // 每次只能删除1个字符串，直到全部删完才能成为空串，删除操作占1个步骤
        //
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        // 初始化第一行，即word1没有字符的情况下，word1从空串转换为word2的第j个字串所需要的最少步骤
        // 每次都需要增加1个字符，增加操作占1个步骤
        for (int i = 1; i <= m; i++) {
            dp[0][i] = dp[0][i - 1] + 1;
        }

        // 递推表达式，假设求 abcde -> fgh 需要多少部
        // “从前往后” 思考对于每一个(i,j)，它从哪里来 (前一个状态通过什么选择才能成为i,j)

        // 1、如果由上一个状态“删除”一个字符得到(i,j)
        // 即已知 abcd -> fgh 需要x步，求abcde -> fgh 需要多少步
        // 显然需要abcde删掉一个字符才能成为abcd，然后由abcd转换为fgh需要x步，加上abcde删掉的1步，总共x+1步
        // 即 dp[i][j] = dp[i-1][j] + 1    word1删除操作

        // 2、如果由上一个状态“增加”一个字符得到(i,j)
        // 即已知 abcde -> fg 需要x步，求abcde -> fgh 需要多少步
        // 显然需要 fg 增加一个字符才能成为 fgh，然后由fgh转换为abcde需要x步，加上fg增加得1步，总共x+1步
        // 即 dp[i][j] = dp[i][j-1] + 1    word2增加操作

        // 3、word1替换一个字符，替换的话，上一个状态的字符长度和当前状态的字符长度相等
        // 即已知 abcd -> fg 需要x步，求abcde -> fgh 需要多少部
        // 当abcde和fgh分别前面1个都已经转换好了，那么对于word1和word2的最后一个字符，只需要一个替换步骤就能够完成了
        // 如果word1和word2的最后一个字符都已经相等了，那么连最后一个替换的步骤都可以剩下来
        // 即 dp[i][j] = dp[i-1][j-1]        if(word1最后一个字符 == word2最后一个字符)
        //    dp[i][j] = dp[i-1][j-1] + 1      word1ui后一个字符 != word2最后一个字符
        //

        // 三种选择全部梳理完成，只需要对其求最小值，即可得到dp[i][j]的最终结果
        // 递推表达式：dp[i][j] = min(min(dp[i-1][j]+1, dp[i][j-1]+1), dp[i-1][j-1]+1)

        // 遍历word1，即遍历行
        for (int i = 1; i <= n; i++) {
            // 遍历word2，即遍历列
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[n][m];
    }
}