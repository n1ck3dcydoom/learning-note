package leetcode.pointoffer;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 *
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * <p>
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 * 示例 2:
 * <p>
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/9/8 14:47
 **/
public class PointOffer_60 {

    public static void main(String[] args) {
        for (double d : twoSum(3)) {
            System.out.print(d + " ");
        }
    }

    public static double[] twoSum(int n) {
        if (n == 1) {
            return new double[]{1 / 6D, 1 / 6D, 1 / 6D, 1 / 6D, 1 / 6D, 1 / 6D};
        }
        int sum = 0;
        double[] res = new double[6 * n + 1];
        // n个骰子，数组从1到n，长度多1
        // 所有可能出现的点数可能为 n ~ 6*n ，长度多1
        int[][] dp = new int[n + 1][6 * n + 1];
        // 初始化dp数组
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }
        // 构造dp数组，从第二颗骰子开始遍历到第n颗
        for (int i = 2; i <= n; i++) {
            // 每颗骰子下，从n开始遍历到6*n，表示每种点数出现的次数
            for (int j = i; j <= 6 * n; j++) {
                // 根据递推关系式得到当前骰子下的结果
                for (int k = 1; k <= 6; k++) {
                    if (j - k < 0) {
                        break;
                    }
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        // 求和
        for (int i = n; i <= 6 * n; i++) {
            sum += dp[n][i];
        }
        double[] realRes = new double[res.length - n];
        for (int i = n; i <= 6 * n; i++) {
            realRes[i - n] = (double) dp[n][i] / sum;
        }
        return realRes;
    }
}