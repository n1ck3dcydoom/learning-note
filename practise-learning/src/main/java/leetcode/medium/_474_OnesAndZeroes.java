package leetcode.medium;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
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
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ones-and-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/10/29 19:16
 **/
public class _474_OnesAndZeroes {

    public static void main(String[] args) {
        String[] strs = new String[]{"10", "0001", "111001", "1", "0"};
        System.out.println(findMaxForm(strs, 5, 3));
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        // 初始化dp数组多一个位置，下标取1到strs.length()
        // m和n数组，由于存在0个、1个到m个，这里共m+1个
        // n数组同理
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        dp[0][0][0] = 0;
        for (int x = 1; x <= strs.length; x++) {
            // x-1表示当前选择的字符串，因为dp数组0号位置废弃，下标从1到strs.length()
            int count0 = getCountZero(strs[x - 1]);
            int count1 = strs[x - 1].length() - count0;
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    // 如果选入第x个str，但是strs[x]的0或者1的个数有一个或者两个超过了背包容量，则不能选入strs[x]
                    if (count0 > i || count1 > j) {
                        dp[x][i][j] = dp[x - 1][i][j];
                    } else {
                        // 如果选入第x个str，且strs[x]的0和1的个数都没有超过背包的容量，则当前dp[x]=dp[x-1]+1
                        dp[x][i][j] = Math.max(dp[x - 1][i][j], dp[x - 1][i - count0][j - count1] + 1);
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }

    // 计算得到length[i]和cnt[i]
    private static int getCountZero(String str) {
        return str.replace("1", "").length();
    }
}