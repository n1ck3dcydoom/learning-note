package algorithm.leetcode.primeAlgorithm.day7;

public class _695_MaxAreaOfIsland {

    public static void main(String[] args) {
        int[][] grid = new int[][] { { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, { 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 }, { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 } };

        System.out.println(maxAreaOfIsland(grid));
    }

    private static int res = 0;

    public static int maxAreaOfIsland(int[][] grid) {
        int l = grid.length;
        int w = grid[0].length;
        // 标记数组
        boolean[][] visited = new boolean[l][w];

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < w; j++) {
                // 只查找陆地，且未被访问
                if (grid[i][j] == 1 && !visited[i][j]) {
                    res = Math.max(res, dfs(visited, grid, i, j));
                    System.out.println(res);
                }
            }
        }
        return res;
    }

    private static int dfs(boolean[][] visited, int[][] grid, int x, int y) {
        if (!inSide(visited, grid, x, y)) {
            return 0;
        }
        // 标记已经访问当前格子
        visited[x][y] = true;
        int area = 1;
        // 访问上边
        area += dfs(visited, grid, x - 1, y);
        // 访问下边
        area += dfs(visited, grid, x + 1, y);
        // 访问左边
        area += dfs(visited, grid, x, y - 1);
        // 访问右边
        area += dfs(visited, grid, x, y + 1);
        // 返回当前岛屿面积
        return area;
    }

    private static boolean inSide(boolean[][] visited, int[][] grid, int x, int y) {
        // 在边界范围内，且是陆地，且未被访问，返回true
        return (0 <= x && x < grid.length) && (0 <= y && y < grid[0].length)
                && (grid[x][y] == 1 && visited[x][y] == false);
    }
}
