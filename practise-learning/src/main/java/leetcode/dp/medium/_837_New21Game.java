package leetcode.dp.medium;

/**
 * @author zhanglei
 * @version 1.0
 * @description zl
 * 爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：
 * <p>
 * 爱丽丝以 0 分开始，并在她的得分少于 K 分时抽取数字。 抽取时，她从 [1, W] 的范围中随机获得一个整数作为分数进行累计，其中 W 是整数。 每次抽取都是独立的，其结果具有相同的概率。
 * <p>
 * 当爱丽丝获得不少于 K 分时，她就停止抽取数字。 爱丽丝的分数不超过 N 的概率是多少？
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：N = 10, K = 1, W = 10
 * 输出：1.00000
 * 说明：爱丽丝得到一张卡，然后停止。
 * 示例 2：
 * <p>
 * 输入：N = 6, K = 1, W = 10
 * 输出：0.60000
 * 说明：爱丽丝得到一张卡，然后停止。
 * 在 W = 10 的 6 种可能下，她的得分不超过 N = 6 分。
 * 示例 3：
 * <p>
 * 输入：N = 21, K = 17, W = 10
 * 输出：0.73278
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= K <= N <= 10000
 * 1 <= W <= 10000
 * 如果答案与正确答案的误差不超过 10^-5，则该答案将被视为正确答案通过。
 * 此问题的判断限制时间已经减少。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/new-21-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/1/12 22:42
 **/
public class _837_New21Game {

    // 0 <= K <= N <= 10000
    // 1 <= W <= 10000
    public double new21Game(int N, int K, int W) {
        // 第一步、定义dp数组
        // 定义dp[i]表示当前手上得分为i时，获胜的机会
        // 所有可能出现的分数情况
        // k+w-1：如果某次分数为k-1了，爱丽丝仍然需要抽牌，范围在[1~w]
        // 所以她在k-1分时，最少能获得k分，最对能获得k-1+w分即k+w-1
        // 0、1、2、3、4、。。。。。、k-1、k、k+1、k+2、。。。。。、N、N+1、。。。。。、k+w-1
        // |-------第一段------------|-----------第二段----------|--------第三段-------|
        // 当前手上分数为i时，有三种可能
        // 1、如果i<k，则继续抽牌
        // 2、如果k<=i<=N，则停止抽牌，且获胜（停止抽牌时，手上的得分没有超过N）
        // 此时获胜的概率是1
        // 3、如果N<i<=k+w-1，则停止抽牌，且输掉（停止抽牌时，手上的得分超过了N）
        // 此时获胜的概率是0
        //
        // 每次抽牌的概率是相等的，综上所述，当手上得分是i时，她获胜的概率应该是
        // dp[i] = (dp[i+1] + dp[i+2] + ... + dp[i+w])/w   0<=i<k(因为i=k时已经停止抽牌且获胜了)
        if (K == 0) {
            // k=0 时，不抽牌，得分为0，无论N为多少，都获胜
            return 1;
        }
        // 得分下标从0开始，直到k+w-1，长度为k+w
        double[] dp = new double[K + W];

        // 第二步、初始值
        // 由于这个递推关系式第i项和后面的i+w项有关，所以初始值应该从最后往前设置，而非dp[0]
        // 从得分区间上来看，可以分为三段
        // 考虑第二段，此时 k<=i<=N，停止抽牌且得分不超过N，获胜，所以dp[k]~dp[N] = 1
        // 考虑第三段，此时 N<i<=k+w-1，停止抽牌且得分超过N，输掉，所以dp[N+1]~dp[k+w-1] = 0

        // 每一轮需要计算i到i+w之间的w个数的概率和
        // 下一轮计算i-1到i+w-1之间的w个数的概率和，实质上是上一轮的结果减掉第i+w-1的概率，加上i-1的概率
        // 即sum[i-1] = sum[i] - dp[i+w-1] + dp[i-1]
        double sum = 0;
        for (int i = K; i <= N; i++) {
            dp[i] = 1;
            sum += dp[i];
        }
        for (int i = N + 1; i < K + W; i++) {
            dp[i] = 0;
        }

        // 第三步、选择，即递推表达式
        // dp[i] = (dp[i+1] + dp[i+2] + ... + dp[i+w])/w   0<=i<k(因为i=k时已经停止抽牌且获胜了)
        for (int i = K - 1; i >= 0; i--) {
            // dp[i] = (dp[i+1] + dp[i+2] + ... + dp[i+w])/w
            // 当i时，sum = dp[i+1] + dp[i+2] + ... + dp[i+w]
            // dp[i] = sum / w
            dp[i] = sum / W;
            // 为了下一轮计算i-1，需要先求的下一轮的sum，只需要减去上一轮的最后一个，加上上一轮的第一个
            // 当i-1时，sum = sum - dp[i+w] + dp[i]
            // dp[i-1] = sum / w
            sum = sum - dp[i + W] + dp[i];
        }
        return dp[0];
    }
}