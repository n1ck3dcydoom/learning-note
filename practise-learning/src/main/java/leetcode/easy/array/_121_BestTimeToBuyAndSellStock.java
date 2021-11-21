package leetcode.easy.array;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.SourceDataLine;

/**
 * @author n1ck3dcydoom
 * @version 1.0 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *          如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。 注意：你不能在买入股票前卖出股票。
 * 
 *          示例 1:
 * 
 *          输入: [7,1,5,3,6,4] 输出: 5 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 =
 *          6）的时候卖出，最大利润 = 6-1 = 5 。 注意利润不能是 7-1 = 6,
 *          因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 
 *          示例 2: 输入: [7,6,4,3,1] 输出: 0 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * 
 *          来源：力扣（LeetCode）
 *          链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 *          著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/8/18 22:25
 **/
public class _121_BestTimeToBuyAndSellStock {
    /**
     * 贪心算法，遍历数组，每次更新最小值且最小值坐标 < 当前坐标，用当前值减去最小值为当次遍历的收益，收益最大值为答案
     * 
     * @param prices 股票价格数组
     * @return 最大收益
     */
    public static int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        // 最小值
        int min = prices[0];
        // 最大收益
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            // 更新最小值
            min = Math.min(min, prices[i]);
            // 更新最大收益
            maxProfit = Math.max(maxProfit, prices[i] - min);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 7, 1, 5, 3, 6, 4 };
        System.out.println(maxProfit(nums));
    }
}