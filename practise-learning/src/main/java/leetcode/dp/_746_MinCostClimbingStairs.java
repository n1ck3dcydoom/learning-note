package leetcode.dp;

/**
 * @author zhanglei
 * @version 1.0
 * @description zl
 * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
 * <p>
 * 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
 * <p>
 * 请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：cost = [10, 15, 20]
 * 输出：15
 * 解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
 *  示例 2：
 * <p>
 * 输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * 输出：6
 * 解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。
 *  
 * <p>
 * 提示：
 * <p>
 * cost 的长度范围是 [2, 1000]。
 * cost[i] 将会是一个整型数据，范围为 [0, 999] 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/1/6 20:20
 **/
public class _746_MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null) {
            return 0;
        }
        int n = cost.length;
        if (n == 0 || n == 1) {
            return 0;
        }
        // 第一步、定义dp数组
        // 定义dp[i]表示到达i层所需要的最小体力
        int[] dp = new int[n + 1];

        // 第二步、选择，即递推表达式
        // 1、从i-1走一步到i层，只需要先用到达i-1层的最小体力加上cost[i-1]即可，即dp[i] = dp[i-1] + cost[i-1]
        // 2、从i-2走两步到i层，只需要先用到达i-2层的最小体力加上cost[i-2]即可，即dp[i] = dp[i-2] + cost[i-2]
        // 综上所述： dp[i] = min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2]
        // 分析题目，cost[n]个台阶，下标从0到n-1，走到n层即跨越n-1走到顶层

        // 第三步、初始值
        // 由于可以选择从第1层，或者第2层出发，相当于走到第1层和走到第2层的体力花费为0 !!!!!
        // dp[0] = dp[1] = 0
        // 走到第3层，需要从第1层跨2步，或者第2层跨1步，即dp[2] = min(dp[0]+cost[0], dp[1]+cost[1])
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[n];
    }
}