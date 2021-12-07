package leetcode.bfs.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class _1034_ColoringBorder {

    public static void main(String[] args) {
        int[][] g1 = new int[][]{{1, 2, 1, 2, 1, 2}, {2, 2, 2, 2, 1, 2}, {1, 2, 2, 2, 1, 2}};
        int[][] r1 = colorBorder(g1, 1, 3, 1);
        for (int i = 0; i < r1.length; i++) {
            for (int j = 0; j < r1[0].length; j++) {
                System.out.print(r1[i][j] + " ");
            }
            System.out.println("\n");
        }
    }

    public static int[][] colorBorder(int[][] grid, int row, int col, int color) {
        // bfs搜索每个给定网格的连通分量

        int curColor = grid[row][col];
        // 上下左右四个方向
        int[][] ways = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // 访问数组
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        // 使用队列保存遍历过程的中间结果
        Deque<int[]> queue = new ArrayDeque<>();
        // 头节点入队
        queue.offer(new int[]{row, col});
        // 开始搜索
        while (!queue.isEmpty()) {
            // 标记已访问
            int[] node = queue.pollFirst();
            int a = node[0];
            int b = node[1];
            visited[a][b] = true;
            // 判断是否是连通分量的边界
            if (board(grid, visited, ways, a, b, curColor)) {
                grid[a][b] = color;
            }
            // 遍历节点的四个方向(其实是只遍历连通分量)
            for (int j = 0; j < 4; j++) {
                int[] way = ways[j];
                if (can(grid, visited, a + way[0], b + way[1], curColor)) {
                    queue.offer(new int[]{a + way[0], b + way[1]});
                }
            }
        }
        return grid;
    }

    private static boolean can(int[][] grid, boolean[][] visited, int i, int j, int color) {
        return (0 <= i && i < grid.length) && (0 <= j && j < grid[0].length) && !visited[i][j] && grid[i][j] == color;
    }

    private static boolean board(int[][] grid, boolean[][] visited, int[][] ways, int i, int j, int color) {
        // 到达边界
        if (0 == i || grid.length - 1 == i || 0 == j || grid[0].length - 1 == j) {
            return true;
        }
        // 上下左右至少存在一个方向跟自己的颜色不同，且没有被访问过
        for (int k = 0; k < 4; k++) {
            int[] way = ways[k];
            if (grid[i + way[0]][j + way[1]] != color && !visited[i + way[0]][j + way[1]]) {
                return true;
            }
        }
        return false;
    }
}
