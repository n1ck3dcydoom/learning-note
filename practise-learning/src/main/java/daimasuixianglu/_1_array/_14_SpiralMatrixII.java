package daimasuixianglu._1_array;

public class _14_SpiralMatrixII {

    public static void main(String[] args) {
        int[][] res = generateMatrix(7);
        for (int[] nums : res) {
            for (int num : nums) {
                System.out.print(num + " ");
            }
            System.out.println("\n");
        }
    }

    public static int[][] generateMatrix(int n) {
        // 矩阵边长等于n
        int[][] res = new int[n][n];
        // 定义四个边界top bottom left right
        int top = 0, left = 0;
        int bottom = n - 1, right = n - 1;
        // 填充数从[1,n]
        int num = 1;
        while (num <= n * n) {
            // 上边界填充顺序从左往右
            int l = left;
            while (l <= right) {
                res[top][l++] = num++;
            }
            top++;
            // 右边界填充顺序从上往下
            int t = top;
            while (t <= bottom) {
                res[t++][right] = num++;
            }
            right--;
            // 下边界填充顺序从右往左
            int r = right;
            while (r >= left) {
                res[bottom][r--] = num++;
            }
            bottom--;
            // 左边界填充顺序从下往上
            int b = bottom;
            while (b >= top) {
                res[b--][left] = num++;
            }
            left++;
        }
        return res;
    }
}
