package algorithm.leetcode.primeDp.day13;

import java.util.ArrayList;
import java.util.List;

public class _120_Triangle {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        l1.add(2);
        triangle.add(l1);
        List<Integer> l2 = new ArrayList<>();
        l2.add(3);
        l2.add(4);
        triangle.add(l2);
        List<Integer> l3 = new ArrayList<>();
        l3.add(6);
        l3.add(5);
        l3.add(7);
        triangle.add(l3);
        List<Integer> l4 = new ArrayList<>();
        l4.add(4);
        l4.add(1);
        l4.add(8);
        l4.add(3);
        triangle.add(l4);

        System.out.println(minimumTotal(triangle));
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        int m = triangle.size();
        int n = triangle.get(m - 1).size();
        // 定义dp[i][j]表示到达第i层第j列所需要的最短路径
        int[][] dp = new int[m][n];
        // 初始值
        dp[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < n; i++) {
            int[] pre = dp[i - 1];
            for (int j = 0; j <= i; j++) {
                int cost = triangle.get(i).get(j);
                // 只能来自于上一层的左上或者上方
                if (j == 0) {
                    dp[i][j] = pre[0] + cost;
                } else if (j == i) {
                    dp[i][j] = pre[j - 1] + cost;
                } else {
                    dp[i][j] = Math.min(pre[j], pre[j - 1]) + cost;
                }
            }
        }
        // 最后一层取最小值
        int res = Integer.MAX_VALUE;
        for (int i : dp[m - 1]) {
            res = Math.min(i, res);
        }
        return res;
    }
}