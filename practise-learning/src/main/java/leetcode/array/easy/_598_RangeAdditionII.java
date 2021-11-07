package leetcode.array.easy;

public class _598_RangeAdditionII {

    public static void main(String[] args) {
        //        int[][] ops1 = new int[][]{{2, 2}, {3, 3}};
        //        System.out.println(maxCount(3, 3, ops1));
        //        int[][] ops2 = new int[][]{{2, 2}, {3, 3}, {3, 3}, {3, 3}, {2, 2}, {3, 3}, {3, 3}, {3, 3}, {2, 2}, {3, 3}, {3, 3}, {3, 3}};
        //        System.out.println(maxCount(3, 3, ops2));
        int[][] ops3 = new int[][]{};
        System.out.println(maxCount(3, 3, ops3));
        System.out.println(maxCount(40000, 40000, ops3));
    }

    public static int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0) {
            return m * n;
        }
        // 操作ops为二维数组int[][2]
        // 其中每个元素int[2]{a,b}表示将所有0 <= i < a 且 0 <= j < b的元素+1
        int[][] matrix = new int[m][n];
        int max = 0;
        // 遍历ops数组
        for (int[] op : ops) {
            int a = op[0];
            int b = op[1];
            // 在matrix数组中操作累加
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    matrix[i][j]++;
                    max = Math.max(max, matrix[i][j]);
                }
            }
        }
        // 遍历matrix数组，统计最大值个数
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == max) {
                    res++;
                }
            }
        }
        return res;
    }

    public static int maxCount1(int m, int n, int[][] ops) {
        if (ops.length == 0) {
            return m * n;
        }
        // 操作ops为二维数组int[][2]
        // 其中每个元素int[2]{a,b}表示将所有0 <= i < a 且 0 <= j < b的元素+1

        // 朴素遍历会超时，观察发现
        // 其最大值个数就是ops操作数组的范围矩阵最多重合的最大面积
        // 每次操作数都是从[0,0]开始，到[a,b]结束，也就是说求ops里面最小的a和最小的b即可
        // 这样的a和b每次操作都能覆盖到

        int a = 1;
        int b = 1;
        for (int[] op : ops) {
            a = Math.min(op[0], a);
            b = Math.min(op[1], b);
        }
        return a * b;
    }
}
