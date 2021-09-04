package algorithm.leetcode.primeAlgorithm.day12;

import java.util.ArrayList;
import java.util.List;

public class _120_Triangle {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        l1.add(2);

        List<Integer> l2 = new ArrayList<>();
        l2.add(3);
        l2.add(4);

        List<Integer> l3 = new ArrayList<>();
        l3.add(6);
        l3.add(5);
        l3.add(7);

        List<Integer> l4 = new ArrayList<>();
        l4.add(4);
        l4.add(1);
        l4.add(8);
        l4.add(3);

        triangle.add(l1);
        triangle.add(l2);
        triangle.add(l3);
        triangle.add(l4);

        System.out.println(minimumTotal1(triangle));
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        // 动态规划

        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        int n = triangle.size();

        // 第一步、设计dp数组
        // 令dp[n]表示到达深度为n的三角形底部所需要的最短步骤
        // n+1额外保存一层都没有的情况
        // int[] dp = new int[n + 1];

        // 第二步、初始值
        // 如果三角形一层都没有则所需路径为0
        // 如果三角形只有一层，则所需路径为顶点路径值
        // dp[0] = 0;
        // dp[1] = triangle.get(0).get(0);
        // 定义到这里发现，少了一个状态，即从上一层的哪个位置进入下一层
        // 所以反推回去dp数组的定义出了问题

        // 重新设计dp数组
        // 令dp[i][j]表示到达第i层第j个位置所需要的最短路径
        int h = triangle.get(n - 1).size();
        int[][] dp = new int[n + 1][h + 1];

        // 初始值
        dp[1][1] = triangle.get(0).get(0);

        // 第三步、递推表达式
        // 考虑dp[i][j]时，如果(i, j)上面相邻的两个节点(i-1, j-1)(i-1, j)都存在
        // 则dp[i][j]等于上面两个相邻节点较小值加上(i, j)的权值
        // dp[i][j]=min(dp[i-1][j-1], dp[i-1][j]) + triangle(i, j)

        // 开始填表，二维表格，且根据约束条件，只需要完成下半部分的填写
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                // 左上方
                int leftUp = Integer.MAX_VALUE;
                // 上方
                int up = Integer.MAX_VALUE;

                // 只要j>1都有左上方的节点
                if (j > 1) {
                    leftUp = dp[i - 1][j - 1];
                }
                // 只要j<i，都有上方的节点
                if (j < i) {
                    up = dp[i - 1][j];
                }
                // 求较小值
                dp[i][j] = Math.min(leftUp, up) + triangle.get(i - 1).get(j - 1);
            }
        }

        int res = Integer.MAX_VALUE;

        for (int i = 1; i < dp[n].length; i++) {
            res = Math.min(res, dp[n][i]);
        }
        return res;
    }

    public static int minimumTotal1(List<List<Integer>> triangle) {
        // 通过二维dp发现，每层的状态之和上一层有关系，可以复用上一层的状态直接写入
        // 优化为一维dp

        // 这里需要额外考虑一点，覆盖写导致的数据丢失问题
        // 考虑遍历到(i,j)时
        // 如果正向遍历，那么dp[i,j]的值会写入到dp[i-1,j]
        // 那么在遍历到(i,j+1)时，dp[i,j+1]的值依赖于上一层的dp[i-1,j-1]和dp[i-1,j]
        // 由于前面已经修改了dp[i-1,j]的值，所以这里的dp[i,j+1]就会发生错误
        // 需要通过反向遍历解决覆盖写导致的数据丢失问题

        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        int n = triangle.size();
        int h = triangle.get(n - 1).size();

        // 额外保存0的位置
        int[] dp = new int[n + 1];

        // 初始化
        dp[1] = triangle.get(0).get(0);

        // 遍历填表，仍然是二维表格，但是使用一维数组保存dp结果
        for (int i = 2; i <= n; i++) {
            // 反向遍历
            for (int j = i; j >= 1; j--) {
                // 左上方
                int leftUp = Integer.MAX_VALUE;
                // 上方
                int up = Integer.MAX_VALUE;
                // 只要j>1都有左上方的节点
                if (j > 1) {
                    leftUp = dp[j - 1];
                }
                // 只要j<i，都有上方的节点
                if (j < i) {
                    up = dp[j];
                }
                // 求较小值
                dp[j] = Math.min(leftUp, up) + triangle.get(i - 1).get(j - 1);
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= h; i++) {
            res = Math.min(res, dp[i]);
        }
        return res;
    }
}
