package leetcode.array.medium;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * <p>
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * <p>
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * 示例 3：
 * <p>
 * 输入：matrix = [[1]]
 * 输出：[[1]]
 * 示例 4：
 * <p>
 * 输入：matrix = [[1,2],[3,4]]
 * 输出：[[3,1],[4,2]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * matrix.length == n
 * matrix[i].length == n
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-image
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/4/26 21:44
 **/
public class _48_Rotate_Image {
    public void rotate(int[][] matrix) {

        int n = matrix.length;

        // 朴素翻转容易得出结论：
        // 对于任意位置 (i, j)，顺时针翻转90°后所在的位置是(j, n-i-1)
        // matrix[i, j] = matrix[j, n-i-1]

        // 先水平翻转矩阵
        // 对于任意位置 (i, j)，按照中轴线水平翻转后所在的位置是 (n-i-1, j)
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
        // 再沿着对角线翻转
        // 对于任意位置 (i, j)，按照左上右下的对角线翻转后所在的位置 (j , i)
        // 只用遍历对角线的一半数据即可
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }
}