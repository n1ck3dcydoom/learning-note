package leetcode.dfs.hard;

import java.util.HashMap;
import java.util.HashSet;

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

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 0}, {0, 1}};
        System.out.println(largestIsland(grid));
    }

    public static int largestIsland(int[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int n = grid.length;
        int m = grid[0].length;

        // 用于保存编号为k的岛屿，面积为s(k)
        int k = 2;
        HashMap<Integer, Integer> areaMap = new HashMap<>();
        // 最大面积
        int res = 0;
        // 遍历所有陆地，找到所有岛屿，并且标记岛屿的面积
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int temp = dfs(grid, i, j, k);
                    res = Math.max(res, temp);
                    areaMap.put(k++, temp);
                }
            }
        }
        // 遍历所有海洋，找到每个与岛屿相邻的海洋，并且计算填充当前海洋后，连接的新岛屿的最大面积
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    // 只找与岛屿相邻的海洋，孤独的海洋就不重复计算了
                    // 判断海洋的四个方向是否和岛屿相邻，填海之后的新岛屿面积等于相邻的所有种类的岛屿面积之和+1，重复相邻的只计算一次
                    HashSet<Integer> islandSet = new HashSet<>();

                    int temp = 1;

                    // 上
                    if (arrivedSide(grid, i - 1, j) && grid[i - 1][j] != 0 && !islandSet.contains(grid[i - 1][j])) {
                        islandSet.add(grid[i - 1][j]);
                        temp += areaMap.get(grid[i - 1][j]);
                    }
                    // 下
                    if (arrivedSide(grid, i + 1, j) && grid[i + 1][j] != 0 && !islandSet.contains(grid[i + 1][j])) {
                        islandSet.add(grid[i + 1][j]);
                        temp += areaMap.get(grid[i + 1][j]);
                    }
                    // 左
                    if (arrivedSide(grid, i, j - 1) && grid[i][j - 1] != 0 && !islandSet.contains(grid[i][j - 1])) {
                        islandSet.add(grid[i][j - 1]);
                        temp += areaMap.get(grid[i][j - 1]);
                    }
                    // 右
                    if (arrivedSide(grid, i, j + 1) && grid[i][j + 1] != 0 && !islandSet.contains(grid[i][j + 1])) {
                        islandSet.add(grid[i][j + 1]);
                        temp += areaMap.get(grid[i][j + 1]);
                    }
                    res = Math.max(res, temp);
                }
            }
        }
        return res;
    }

    private static int dfs(int[][] grid, int i, int j, int k) {
        // 超出边界了，面积为0
        if (!arrivedSide(grid, i, j)) {
            return 0;
        }
        // 不是陆地，面积为0
        if (grid[i][j] != 1) {
            return 0;
        }
        // 标记当前陆地已经被访问过，用k值不同区分不同的岛屿
        grid[i][j] = k;

        // 递归找到岛屿的所有陆地，计算面积，递归一定从陆地开始，有初始值1
        return 1 + dfs(grid, i + 1, j, k) + dfs(grid, i - 1, j, k) + dfs(grid, i, j + 1, k) + dfs(grid, i, j - 1, k);
    }

    /**
     * 当前网格(i,j)处于grid的范围内，返回true
     * 否则返回false
     */
    private static boolean arrivedSide(int[][] grid, int i, int j) {
        return (0 <= i && i < grid.length) && (0 <= j && j < grid[0].length);
    }
}
