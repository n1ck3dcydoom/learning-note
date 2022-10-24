package leetcode.dfs.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by n!Ck
 * Date: 2022/10/25
 * Time: 00:16
 * Description:
 */

public class _934_ShortestBridge {

    // 上下左右
    private static int[][] ways = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // 记录 bfs 搜索的层深
    private static int depth = 0;

    public static void main(String[] args) {
        // System.out.println(shortestBridge(new int[][]{
        //         {1, 1, 1, 1, 1},
        //         {1, 0, 0, 0, 1},
        //         {1, 0, 1, 0, 1},
        //         {1, 0, 0, 0, 1},
        //         {1, 1, 1, 1, 1}}));
        // System.out.println(shortestBridge(new int[][]{
        //         {0, 1},
        //         {1, 0}
        // }));
        System.out.println(shortestBridge(new int[][]{
                {0, 1, 0},
                {0, 0, 0},
                {0, 0, 1}
        }));
    }

    public static int shortestBridge(int[][] grid) {
        // 通过 dfs 搜索并分别使用 1 和 2 标记两个岛屿
        // 从任意一个岛屿出发,使用 bfs 按层搜索,直到和另外一个岛屿相连,搜索的层深度则是最短路径

        int m = grid.length;
        int n = grid[0].length;

        // dfs 搜索并标记两个岛屿
        int mark = 1;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    dfs(grid, visited, i, j, mark++);
                }
            }
        }
        // 从第二个岛开始 bfs 搜索,直到和第一个岛相遇,搜索的层深度就是最短路径
        bfs(grid);
        return depth;
    }

    private static void dfs(int[][] grid, boolean[][] visited, int i, int j, int mark) {
        // 超出边界
        if (overside(grid, i, j)) {
            return;
        }
        // 跳过海洋
        if (grid[i][j] == 0) {
            return;
        }
        // 跳过已搜索过的陆地
        if (visited[i][j]) {
            return;
        }
        // 标记当前陆地已经搜索过
        visited[i][j] = true;
        grid[i][j] = mark;
        // 继续从四个方向上 dfs 搜索相邻陆地
        for (int k = 0; k < 4; k++) {
            int[] way = ways[k];
            dfs(grid, visited, i + way[0], j + way[1], mark);
        }
    }

    private static void bfs(int[][] grid) {
        // 使用队列保存 bfs 每一层需要搜索的陆地
        Deque<int[]> queue = new ArrayDeque<>();
        // 初始将与海洋相邻的所有节点入队
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2 && checkWithSea(grid, i, j)) {
                    queue.offerLast(new int[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            // 当前层需要搜索的元素个数
            int size = queue.size();
            // 搜索当前层每个元素
            while (size-- > 0) {
                // 头结点出队
                int[] node = queue.pollFirst();

                // 检查是否达到第二个岛屿
                for (int k = 0; k < 4; k++) {
                    int[] way = ways[k];
                    int xx = node[0] + way[0];
                    int yy = node[1] + way[1];
                    // 跳过超出边界的
                    if (overside(grid, xx, yy)) {
                        continue;
                    }
                    // 跳过已经访问过的
                    if (grid[xx][yy] == 2) {
                        continue;
                    }
                    // 已经和岛屿 1 相邻
                    if (grid[xx][yy] == 1) {
                        return;
                    }
                    // 将临海陆地的海洋渲染为岛屿 2 的陆地
                    grid[xx][yy] = 2;
                    // 新的岛屿 2 陆地节点入队
                    queue.offerLast(new int[]{xx, yy});
                }
            }
            // 一层搜索完之后,bfs 搜索深度+1
            depth++;
        }
    }

    private static boolean overside(int[][] grid, int x, int y) {
        int m = grid.length;
        int n = grid[0].length;
        return x < 0 || x >= m || y < 0 || y >= n;
    }

    private static boolean checkWithSea(int[][] grid, int x, int y) {
        // 检查 4 个方向上是否有海洋
        for (int k = 0; k < 4; k++) {
            int[] way = ways[k];
            if (overside(grid, x + way[0], y + way[1])) {
                continue;
            }
            if (grid[x + way[0]][y + way[1]] == 0) {
                return true;
            }
        }
        return false;
    }
}
