package leetcode.dp.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n!Ck
 * Date: 2022/10/27
 * Time: 01:20
 * Description:
 */

public class _1235_MaximumProfitInJobScheduling {

    public static void main(String[] args) {
        // [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
        // System.out.println(jobScheduling(
        //         new int[]{1, 2, 3, 3},
        //         new int[]{3, 4, 5, 6},
        //         new int[]{50, 10, 40, 70}));
        // [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
        System.out.println(
                jobScheduling(new int[]{1, 2, 3, 4, 6}, new int[]{3, 5, 10, 6, 9}, new int[]{20, 20, 100, 70, 60}));
        // [1,1,1], endTime = [2,3,4], profit = [5,6,4]
        // System.out.println(jobScheduling(
        //         new int[]{1, 1, 1},
        //         new int[]{2, 3, 4},
        //         new int[]{5, 6, 4}
        // ));
    }

    static class Work {
        private int s;
        private int e;
        private int p;

        public Work(int s, int e, int p) {
            this.s = s;
            this.e = e;
            this.p = p;
        }
    }

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        // 按照结束时间对每个工作升序排序
        int n = startTime.length;
        List<Work> works = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            works.add(new Work(startTime[i], endTime[i], profit[i]));
        }
        works.sort((w1, w2) -> w1.e - w2.e);

        // 考虑每个工作有选择或者不选择
        // 动态规划
        // 定义 dp[i] 表示前 i 项工作所能取得的最大报酬
        int[] dp = new int[n];
        // 初始状态,第一份工作肯定选择,能取得的最大报酬就等于 p[i]
        dp[0] = works.get(0).p;
        // 状态转移方程,考虑前 i 项工作,若不选择第 i 项工作,则 dp[i]=dp[i-1],这个很简单
        // 若选择第 i 项工作,由于同一时间内不允许并行工作
        // 则必须往前查找到第一个出现的 dp[k],满足 end[k] <= start[i], 则 dp[i] = dp[k] + p[i]
        for (int i = 1; i < n; i++) {
            // 不选择第 i 项工作
            dp[i] = dp[i - 1];
            // 选择第 i 项工作,往前查找第一个满足 end[j] <= start[j] 的位置 j
            int j = i - 1;
            while (j >= 0) {
                if (works.get(j).e <= works.get(i).s) {
                    break;
                }
                j--;
            }
            if (j >= 0) {
                dp[i] = Math.max(dp[i], dp[j] + works.get(i).p);
            } else {
                dp[i] = Math.max(dp[i], works.get(i).p);
            }
        }
        return dp[n - 1];
    }
}
