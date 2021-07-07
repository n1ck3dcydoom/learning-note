package leetcode.greedy.medium;

import java.util.Arrays;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
 * <p>
 * 商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。Tony 一共有 coins 现金可以用于消费，他想要买尽可能多的雪糕。
 * <p>
 * 给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。
 * <p>
 * 注意：Tony 可以按任意顺序购买雪糕。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：costs = [1,3,2,4,1], coins = 7
 * 输出：4
 * 解释：Tony 可以买下标为 0、1、2、4 的雪糕，总价为 1 + 3 + 2 + 1 = 7
 * 示例 2：
 * <p>
 * 输入：costs = [10,6,8,7,7,8], coins = 5
 * 输出：0
 * 解释：Tony 没有足够的钱买任何一支雪糕。
 * 示例 3：
 * <p>
 * 输入：costs = [1,6,3,1,2,5], coins = 20
 * 输出：6
 * 解释：Tony 可以买下所有的雪糕，总价为 1 + 6 + 3 + 1 + 2 + 5 = 18 。
 *  
 * <p>
 * 提示：
 * <p>
 * costs.length == n
 * 1 <= n <= 10^5
 * 1 <= costs[i] <= 10^5
 * 1 <= coins <= 10^8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-ice-cream-bars
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/6 21:22
 **/
public class _1833_MaximumIceCreamBars {
    public int maxIceCream(int[] costs, int coins) {
        // 表面上看是01背包问题
        // 实际上是一个贪心问题
        // 物品的价值都是1，容量是costs[i]，每次选择容量最小购买，即可

        // 先排序，默认从小到大，如果需要逆序从大到小，需要传入自定义的comparetor，或者collectors.reverseOrder
        Arrays.sort(costs);

        int res = 0;
        // 遍历雪糕
        int n = costs.length;
        for (int cost : costs) {
            // 如果可以购买，则买下
            if (coins >= cost) {
                res++;
                coins -= cost;
            } else {
                // 一旦钱不够了，后面剩余的也都不用考虑了，反正都买不起
                break;
            }
        }
        return res;
    }
}