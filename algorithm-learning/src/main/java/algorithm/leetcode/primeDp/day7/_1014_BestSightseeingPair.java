package algorithm.leetcode.primeDp.day7;

public class _1014_BestSightseeingPair {

    public static void main(String[] args) {
        int[] nums = new int[]{8, 1, 5, 2, 6};
        System.out.println(maxScoreSightseeingPair(nums));
    }

    public static int maxScoreSightseeingPair(int[] values) {
        // O(n^2)暴力搜索
        int n = values.length;
        if (n < 2) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                max = Math.max(max, values[i] + values[j] - (j - i));
            }
        }
        return max;
    }

    public static int maxScoreSightseeingPair1(int[] values) {
        // 暴力搜索通过率 49/53，果然超时

        // 第一步、定义dp数组
        // 定义dp[i][j]表示景点i和景点j能够取得的最大观光价值
        // dp[i][j] = v[i]+v[j] - (j-i) = v[i]+i + v[j]-j
        // 其中 vi+i 和 vj-j 是分别独立互不影响的
        // dp[i][j]最大值和vi+i的最大值有关，在计算vj-j的时候可以同时维护vi+i的结果

        int n = values.length;
        if (n < 2) {
            return 0;
        }
        int maxi = values[0] + 0;
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            // 计算vj-j然后加上前面最大的vi+i，得到最大的结果
            res = Math.max(res, maxi + values[i] - i);
            // 更新最大的vi+i
            maxi = Math.max(maxi, values[i] + i);
        }
        return res;
    }
}