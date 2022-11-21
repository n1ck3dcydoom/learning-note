package nowcode.top101;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 13:34
 * Description:
 */

public class _57_岛屿数量 {

    public static void main(String[] args) {
        System.out.println(solve(new char[][]{
                {'1', '1', '0', '0', '0'},
                {'0', '1', '0', '1', '1'},
                {'0', '0', '0', '1', '1'},
                {'0', '0', '0', '0', '0'},
                {'0', '0', '1', '1', '1'}}));
    }

    // 上下左右
    private static int[][] ways = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static int solve(char[][] grid) {
        // 岛屿问题几乎都可以用 bfs 处理
        int res = 0;
        // 岛屿计数
        int k = 2;
        // 遍历所有网格,从陆地开始搜索,将岛屿内的所有陆地标记后结束 bfs,直到遇到下一个岛屿或者搜索完整个网格
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    bfs(grid, i, j);
                }
            }
        }
        return res;
    }

    private static void bfs(char[][] grid, int x, int y) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.pollFirst();
                int xx = cur[0];
                int yy = cur[1];
                // 当前陆地标记为已访问
                grid[xx][yy] = '2';
                // 遍历四个方向
                for (int i = 0; i < 4; i++) {
                    int[] way = ways[i];
                    // 检查是否能到达
                    if (!side(grid, xx + way[0], yy + way[1])) {
                        continue;
                    }
                    // 当前方向下一个节点加入队列
                    queue.offerLast(new int[]{xx + way[0], yy + way[1]});
                }
            }
        }
    }

    private static boolean side(char[][] grid, int x, int y) {
        int m = grid.length;
        int n = grid[0].length;
        return x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1';
    }
}
