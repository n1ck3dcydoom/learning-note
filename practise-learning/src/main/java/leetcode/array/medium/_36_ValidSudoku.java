package leetcode.array.medium;

public class _36_ValidSudoku {

    public static void main(String[] args) {
        char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
        System.out.println(isValidSudoku(board));
    }

    public static boolean isValidSudoku(char[][] board) {
        // 使用三个额外数组分别保存行、列、块某个数字是否出现
        // row[3][6]表示第3行出现了数字6
        boolean[][] row = new boolean[10][10];
        // column[7][4]表示第7列出现了数字4
        boolean[][] column = new boolean[10][10];
        // block[2][5]表示第2块出现了数字5
        boolean[][] block = new boolean[10][10];
        // 直接遍历每个格子，检查当前格子所在行、列、块是否存在相同数字
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    // 判断行
                    if (row[i][board[i][j] - '0'] == true) {
                        return false;
                    } else {
                        row[i][board[i][j] - '0'] = true;
                    }
                    // 判断列
                    if (column[j][board[i][j] - '0'] == true) {
                        return false;
                    } else {
                        column[j][board[i][j] - '0'] = true;
                    }
                    // 判断块
                    // 块的索引分为
                    // 0 1 2
                    // 3 4 5
                    // 6 7 8
                    // 可以看作由0 1 2 分别+0 +3 +6得到
                    // 其中行索引：
                    // +0 +3 +6 行数由i决定，+(i/3)*3
                    // [0,2] -> +(0)*3
                    // [3,5] -> +(1)*3
                    // [6,8] -> +(2)*3
                    // 其中列索引：
                    // 0 1 2 列数由j决定，(j/3)
                    // [0,2] -> j/3 = 0
                    // [3,5] -> j/3 = 1
                    // [6,8] -> j/3 = 2

                    // 判断列
                    if (block[(i / 3) * 3 + (j / 3)][board[i][j] - '0'] == true) {
                        return false;
                    } else {
                        block[(i / 3) * 3 + (j / 3)][board[i][j] - '0'] = true;
                    }
                }
            }
        }
        return true;
    }
}
