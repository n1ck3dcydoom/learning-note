package leetcode.greedy.medium;

public class _807_MaxIncreaseToKeepCitySkyline {

    public static void main(String[] args) {
        int[][] g1 = new int[][]{{3, 0, 8, 4}, {2, 4, 5, 7}, {9, 2, 6, 3}, {0, 3, 1, 0}};
        int[][] g2 = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        System.out.println(maxIncreaseKeepingSkyline(g1));
        System.out.println(maxIncreaseKeepingSkyline(g2));
    }

    public static int maxIncreaseKeepingSkyline(int[][] grid) {
        int sum = 0;
        int n = grid.length;
        //        // 直接贪心
        //        // 对于每个点，它不能超过所在行所在列最大值中的较小值
        //
        //        // 求当前行当前列的最大值中的较小值
        //        for (int i = 0; i < n; i++) {
        //            for (int j = 0; j < n; j++) {
        //                int subMax = 0;
        //                // 当前行
        //                int raw = 0;
        //                for (int k = 0; k < n; k++) {
        //                    raw = Math.max(raw, grid[i][k]);
        //                }
        //                raw = Math.max(raw, grid[i][j]);
        //                // 当前列
        //                int col = 0;
        //                for (int k = 0; k < n; k++) {
        //                    col = Math.max(col, grid[k][j]);
        //                }
        //                subMax = Math.min(raw, col);
        //                // 计算需要增加的高度
        //                sum += subMax - grid[i][j];
        //            }
        //        }
        //        return sum;


        // 优化一下上面的是O(n^3)，优化到O(3n^2)
        int[] raws = new int[n];
        int[] cols = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                raws[i] = Math.max(raws[i], grid[i][j]);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cols[i] = Math.max(cols[i], grid[j][i]);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += Math.min(raws[i], cols[j]) - grid[i][j];
            }
        }
        return sum;
    }
}

