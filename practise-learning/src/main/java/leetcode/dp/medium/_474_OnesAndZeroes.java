package leetcode.dp.medium;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * <p>
 * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
 * <p>
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 * 示例 2：
 * <p>
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i]仅由'0' 和'1' 组成
 * 1 <= m, n <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ones-and-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/6/6 23:33
 **/
public class _474_OnesAndZeroes {
    public int findMaxForm(String[] strs, int m, int n) {
        // 子集问题满足某个条件，动态规划
        // 定义dp[i][j][k]表示，对于前i个元素构成的子集，最多含有j个1和k个0，且字符元素数量最多的字符数量
        // 类似背包问题，把j和k放入容量为i的背包中
        // 存在没有数据被选择的情况，即i,j,k都可能等于0

        int l = strs.length;
        int[][][] dp = new int[l + 1][m + 1][n + 1];
        // 初始值
        // 把0个1和0个0放入容量为0的背包中
        dp[0][0][0] = 0;

        // 遍历物品，即strs字符串的所有元素
        for (int i = 1; i <= l; i++) {
            // 当前物品的价值，即多少个0和多少个1
            int zeroCount = getZero(strs[i - 1]);
            int oneCount = strs[i - 1].length() - zeroCount;

            // 遍历背包容量，能放下多少个0和多少个1
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    // 把第i个物品放入背包，如果0或者1有任何一个超过背包容量，都无法放入物品
                    // 只有第i个字符串的0、1个数都小于背包剩余容量是，才能放入背包
                    if (j >= zeroCount && k >= oneCount) {
                        // 上一个背包剩余体积至少需要zeroCount个0和oneCount个1才能放入
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeroCount][k - oneCount]);
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }
        return dp[l][m][n];
    }

    private int getZero(String str) {
        return str.replaceAll("1", "").length();
    }
}