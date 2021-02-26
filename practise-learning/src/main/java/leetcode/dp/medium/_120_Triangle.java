package leetcode.dp.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * <p>
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 * 2
 * 3 4
 * 6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 示例 2：
 * <p>
 * 输入：triangle = [[-10]]
 * 输出：-10
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 *  
 * <p>
 * 进阶：
 * <p>
 * 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/2/26 20:33
 **/
public class _120_Triangle {

    public static void main(String[] args) {
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

        List<List<Integer>> list = new ArrayList<>();
        list.add(l1);
        list.add(l2);
        list.add(l3);
        list.add(l4);

        System.out.println(minimumTotal1(list));
    }

    public int minimumTotal0(List<List<Integer>> triangle) {
        // 贪心？
        // 第一层只有一个选择，就是当前顶点
        // 每次只能走到下一行的i或者i+1的位置上，只需要取下一行i或者i+1的较小值

        // 初始值就是顶点的权重
        int res = triangle.get(0).get(0);
        // 记录当前行选择的下标pos，那么下一行能够选择的下标只有pos和pos+1
        // 第一行的出发点下标就是0
        int pos = 0;
        // 从第二行开始遍历
        for (int i = 1; i < triangle.size(); i++) {
            res += Math.min(triangle.get(i).get(pos), triangle.get(i).get(pos + 1));
        }
        return res;
        // 贪心算法只能够保证每次选择的最优解，而不是全局的最优解
        //   -1
        //   2,3
        // 1,-1,-3
        // 使用贪心算法肯定会选择-1 -> 2 -> -1 这条路，总路径和是0，看似每一步都选择了最优解
        // 但是并没有构成全局的最优解
        // -1 -> 3 -> -1 ，这条路径的权重和是-1，比贪心算法选出来的路径还要更小
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        // 考察第i层的最短路径和dp[i]，它肯定只能通过第i-1层的最短路径和加上从i-1到i的两个路径选择中的较短权重计算得到
        // 即 dp[i] = dp[i-1] + min(c[pos], c[pos+1])，这里的pos表示从第i-1层通过i层的pos或者pos+1节点到达第i层

        // 分析题目，如何到达第f(i,j)个位置？
        // 简单画图可以得到只能通过f(i-1,j)或者f(i-1,j-1)两个位置得到

        // 分析到这里就发现，需要二维数组来保存dp[i][j]表示从顶点(0,0)到(i,j)的最短路径和
        // dp[i][j] = min(dp[i-1][j], dp[i-1][j-1]) + c[i,j]
        int n = triangle.size();
        int m = triangle.get(n - 1).size();
        int[][] dp = new int[n][m];

        // 初始值，顶点的值
        dp[0][0] = triangle.get(0).get(0);

        // 选择，考虑边界值
        // 如果j=0，那么它只能通过i-1层的j得到，不能通过i-1,j-1得到
        // 如果j=当前层的最后一位置，它只能通过i-1,j-1得到，不能通过i-1,j得到

        for (int i = 1; i < n; i++) {
            // 当前层的权重列表
            List<Integer> currentLayer = triangle.get(i);
            //            int m = i + 1;
            // for (int j = 0; j < m; j++) {
            for (int j = 0; j < i + 1; j++) {
                // 左边界
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + currentLayer.get(j);
                }
                // 右边界
                else if (j == i) {
                    dp[i][j] = dp[i - 1][j - 1] + currentLayer.get(j);
                }
                // 中间部分
                else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + currentLayer.get(j);
                }
            }
        }
        // 求最后一层的最小值即可
        int res = dp[n - 1][0];
        for (int i = 1; i < m; i++) {
            res = Math.min(res, dp[n - 1][i]);
        }
        return res;
    }

    public static int minimumTotal1(List<List<Integer>> triangle) {
        // 通过上面的朴素dp可以简单的发现，每次计算第i行的时候，只用到了第i-1行的结果
        // 考虑把空间压缩到一维，每次计算完成之后写回一维数组

        int n = triangle.size();
        int m = triangle.get(n - 1).size();

        int[] dp = new int[m];

        // 初始值，只有第1个元素有值，就是顶点的权重
        dp[0] = triangle.get(0).get(0);

        // 遍历剩下的行
        for (int i = 1; i < n; i++) {
            // 当前层的权重列表
            List<Integer> currentLayer = triangle.get(i);
            // 遍历每一列
            // 跟背包问题很相似，如果正序遍历，会导致当前行的数据提前写入了上一行，影响计算结果
            for (int j = i; j >= 0; j--) {
                // 考虑右边界情况
                if (j == i) {
                    dp[j] = dp[j - 1] + currentLayer.get(j);
                }
                // 考虑左边界
                else if (j == 0) {
                    dp[j] = dp[j] + currentLayer.get(j);
                }
                // 中间情况
                else {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + currentLayer.get(j);
                }
            }
        }
        // 求最后一层的最小值即可
        int res = dp[0];
        for (int i = 1; i < m; i++) {
            res = Math.min(res, dp[i]);
        }
        return res;
    }
}