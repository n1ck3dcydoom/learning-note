package algorithm.backtracking;

import java.util.Scanner;
import java.util.Vector;

/**
 * @author zhanglei
 * @version 1.0
 * @description 八皇后问题
 * 给定一个 N x N 的棋盘，求 N 个皇后互相不受影响的放置方法
 * @date 2020/4/28 23:07
 **/
public class EightQueen {

    public static void main(String[] args) {
        // 边长
        //        int N;
        //        try (Scanner scanner = new Scanner(System.in)) {
        //            N = scanner.nextInt();
        //        }

        // 4 x 4路径
        String[][] track4 = new String[][]{
                {".", ".", ".", "."},
                {".", ".", ".", "."},
                {".", ".", ".", "."},
                {".", ".", ".", "."},
        };
        // 8 x 8 路径
        String[][] track8 = new String[][]{
                {".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", "."}
        };
        eightQueen(track4, 0, 4);
        eightQueen(track8, 0, 8);
    }

    /**
     * 八皇后问题
     *
     * @param track 路径
     */
    private static void eightQueen(String[][] track, int row, int N) {
        // 结束条件
        // 如果当前行超过了最大行
        if (row == track.length) {
            // 输出当前皇后的放置法
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(track[i][j] + " ");
                }
                System.out.print("\n");
            }
            System.out.print("\n");
            return;
        }
        // 开始遍历选择
        // 选择就是当前行所有的位置
        for (int i = 0; i < N; i++) {
            // 如果当前的选择 不满足 皇后放置的条件，继续遍历选择列表
            if (!canPlaceQueen(track, row, i, N)) {
                continue;
            }
            // 如果当前的选择 满足 皇后放置的条件，做出选择，加入路径，移出选择列表
            // 路径中当前行的当前列放入皇后
            track[row][i] = "Q";
            // 做出选择后开始递归
            // 递归下一行
            eightQueen(track, row + 1, N);
            // 遇到回溯点
            // 从路径中移除当前选择，选择重新加入选择列表
            track[row][i] = ".";
        }

    }


    private static boolean canPlaceQueen(String[][] track, int row, int column, int N) {
        // 每次选择遍历的都是一行的所有位置，所以不用检查当前行其他位置是否有Queen
        // 也不用检查当前行后面的行，因为后面的行肯定没有Queen

        // 看 track[row][column] 所在列有没有其他Queen
        for (int n = 0; n < row; n++) {
            if ("Q".equals(track[n][column])) {
                return false;
            }
        }

        // 看 track[row][column] 所在的两条对角线有没有其他Queen
        // 检查左上方对角线是否有其他Queen
        for (int n = row - 1, m = column - 1; n >= 0 && m >= 0; n--, m--) {
            if ("Q".equals(track[n][m])) {
                return false;
            }
        }
        // 检查右上方对角线是否有其他Queen
        for (int n = row - 1, m = column + 1; n >= 0 && m < N; n--, m++) {
            if ("Q".equals(track[n][m])) {
                return false;
            }
        }
        return true;
    }
}