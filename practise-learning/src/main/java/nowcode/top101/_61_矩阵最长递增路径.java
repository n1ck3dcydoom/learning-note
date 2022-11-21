package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 15:03
 * Description:
 */

public class _61_矩阵最长递增路径 {

    // 上下左右
    private static int[][] ways = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(solve(matrix));
    }

    public static int solve(int[][] matrix) {
        // 将矩阵每两个相邻节点之间，如果值不相等，则看作是有一条从较小值指向较大值的路径
        // 因此可以把矩阵转化为一个有向图，求有向图里面最长路径的节点
        // 对于有向图的处理，可以尝试 dfs 搜索

        int res = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];
        // 从每个节点开始搜索最长路径
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(matrix, memo, i, j));
            }
        }
        return res;
    }

    private static int dfs(int[][] matrix, int[][] memo, int x, int y) {
        // 有向图中,如果一个节点已经搜索过,且记录从当前节点出发的最长路径为 x
        // 那么其他节点在遍历到这个节点的时候,可以直接使用这个已经搜索过的缓存值 x 和当前路径相加
        // 即可得到节点在当前路径上的最大值,避免重复搜索
        if (memo[x][y] != 0) {
            // 如果已经搜索过,直接返回当前节点的缓存值
            return memo[x][y];
        }

        // 初始化每个节点的最长路径长度至少为 1
        int tmp = 1;
        // 从当前节点的上下左右四个方向遍历
        for (int i = 0; i < 4; i++) {
            int[] way = ways[i];
            int xx = x + way[0];
            int yy = y + way[1];
            // 检查当前方向上的节点能否搜索,有向图,要求当前节点的权重小于下一个方向节点的权重
            if (xx >= 0 && xx < matrix.length && yy >= 0 && yy < matrix[0].length && matrix[xx][yy] > matrix[x][y]) {
                // 每次搜索时路径长度+1,
                tmp = Math.max(tmp, dfs(matrix, memo, xx, yy) + 1);
            }
        }
        // 搜索完成之后,把当前节点的结果缓存下来
        memo[x][y] = tmp;
        return tmp;
    }
}
