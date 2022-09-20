package leetcode.dfs.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 在二维地图上， 0代表海洋， 1代表陆地，我们最多只能将一格 0 海洋变成 1变成陆地。
 * <p>
 * 进行填海之后，地图上最大的岛屿面积是多少？（上、下、左、右四个方向相连的 1 可形成岛屿）
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1, 0], [0, 1]]
 * 输出: 3
 * 解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
 * 示例 2:
 * <p>
 * 输入: [[1, 1], [1, 0]]
 * 输出: 4
 * 解释: 将一格0变成1，岛屿的面积扩大为 4。
 * 示例 3:
 * <p>
 * 输入: [[1, 1], [1, 1]]
 * 输出: 4
 * 解释: 没有0可以让我们变成1，面积依然为 4。
 * 说明:
 * <p>
 * 1 <= grid.length = grid[0].length <= 50
 * 0 <= grid[i][j] <= 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/making-a-large-island
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/4/7 21:17
 **/
public class _827_MakingALargeIsland {

    // 上下左右
    private static int[][] ways = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // 使用 map 记录每个岛屿
    private static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(largestIsland(new int[][]{{1, 1}, {1, 0}}));
    }

    public static int largestIsland(int[][] grid) {
        // 首先统计所有岛屿的面积,记录下构成岛屿的每个陆地的映射关系,方便后面查找
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        // 为了区分 0 和 1,使用 2 开始记号
        int k = 2;
        // 记录岛屿的最大面积
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 仅查找陆地
                if (grid[i][j] == 1) {
                    int area = dfs(grid, visited, i, j, k);
                    map.put(k++, area);
                    max = Math.max(max, area);
                }
            }
        }

        int res = 0;
        // 遍历所有海洋
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int temp = 1;
                    Set<Integer> set = new HashSet<>();
                    for (int l = 0; l < 4; l++) {
                        int[] way = ways[l];
                        int xx = i + way[0];
                        int yy = j + way[1];
                        if (inSide(grid, xx, yy) && !set.contains(grid[xx][yy])) {
                            temp += map.getOrDefault(grid[xx][yy], 0);
                            set.add(grid[xx][yy]);
                        }
                    }
                    res = Math.max(res, temp);
                }
            }
        }
        return Math.max(max, res);
    }

    private static int dfs(int[][] grid, boolean[][] visited, int x, int y, int k) {
        // 超出边界或者已经访问过
        if (!inSide(grid, x, y) || visited[x][y] || grid[x][y] == 0) {
            return 0;
        }
        // 将当前标记为 visited
        visited[x][y] = true;
        // 将当前陆地标记为岛屿编号 k
        grid[x][y] = k;
        // 从四个方向上计算岛屿面积
        int area = 1;
        for (int i = 0; i < 4; i++) {
            int[] way = ways[i];
            int xx = x + way[0];
            int yy = y + way[1];
            area += dfs(grid, visited, xx, yy, k);
        }
        return area;
    }

    private static boolean inSide(int[][] grid, int x, int y) {
        int m = grid.length;
        int n = grid[0].length;
        return 0 <= x && x < m && 0 <= y && y < n;
    }
}
