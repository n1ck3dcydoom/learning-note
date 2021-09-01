package algorithm.leetcode.primeAlgorithm.day9;

import java.util.ArrayDeque;
import java.util.Deque;

public class _994_RottingOranges {

    public static void main(String[] args) {
        int[][] grid = new int[][] { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
        System.out.println(orangesRotting(grid));
    }

    public static int orangesRotting(int[][] grid) {
        // 每次从腐烂的橘子开始一层一层遍历，思路为bfs
        // 每次可能有多个腐烂的橘子同时开始向四周扩散，思路为多源bfs
        // 多源bfs转换为单源bfs：超级腐烂节点

        int l = grid.length;
        int w = grid[0].length;
        // 使用队列保存bfs每层遍历的结果
        Deque<int[]> queue = new ArrayDeque<>();
        // 保存每个橘子的访问信息
        boolean[][] visited = new boolean[l][w];

        // 结束时需要判断是否还剩余新鲜橘子，第一次遍历的时候就先统计所有新鲜橘子的个数
        int count = 0;
        // 所有腐烂橘子加入队列，构成超级腐烂节点
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < w; j++) {
                if (grid[i][j] == 2) {
                    queue.addLast(new int[] { i, j });
                    visited[i][j] = true;
                }
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }
        int[] dx = new int[] { -1, 1, 0, 0 };
        int[] dy = new int[] { 0, 0, -1, 1 };
        // 遍历队列，同时需要记录层数
        // int h = -1;
        int h = 0;
        // 终止条件是队列为空，或者所有的新鲜橘子都腐烂了
        while (!queue.isEmpty() && count > 0) {
            // 当前层需要遍历的节点个数
            int size = queue.size();
            // 遍历当前层所有节点
            for (int i = 0; i < size; i++) {
                // 队头节点出队
                int[] node = queue.pollFirst();
                int x = node[0];
                int y = node[1];
                // 检查上下左右四个方向的节点
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (inSide(grid, nx, ny)) {
                        // 新橘子入队
                        queue.addLast(new int[] { nx, ny });
                        // 新鲜橘子变为腐烂橘子
                        grid[nx][ny] = 2;
                        count--;
                    }
                }
            }
            // 层数+1
            h++;
        }
        // return h;
        // 最后需要判断一下还有没有新鲜橘子
        return count == 0 ? h : -1;
    }

    private static boolean inSide(int[][] grid, int x, int y) {
        return (0 <= x && x < grid.length) && (0 <= y && y < grid[0].length) && (grid[x][y] == 1);
    }
}
