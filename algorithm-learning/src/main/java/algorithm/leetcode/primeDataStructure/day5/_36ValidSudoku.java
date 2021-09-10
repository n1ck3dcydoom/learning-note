package algorithm.leetcode.primeDataStructure.day5;

public class _36ValidSudoku {

    public static void main(String[] args) {
        char[][] board = new char[][] { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
        System.out.println(isValidSudoku(board));
    }

    public static boolean isValidSudoku(char[][] board) {
        // 记录某行所有出现的数字，9行，每行9个数字
        boolean[][] row = new boolean[9][9];
        // 记录某列所有出现的数字，9列，每列9个数字
        boolean[][] column = new boolean[9][9];
        // 记录某个3*3块所有出现的数字，9块，每块9个数字
        boolean[][] block = new boolean[9][9];

        // 遍历整个数独
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    // char转数字
                    int num = board[i][j] - '0';
                    // 如果行、列或者块出现了这个数字
                    // 块的计算方式
                    // i: [0,2] = 0 [3, 5] = 1 [6, 8] = 2
                    // j: [0,2] = 0 [3, 5] = 1 [6, 8] = 2
                    // (i, j):
                    // (0, 0) = 0 (0 ,1) = 1 (0, 2) = 2
                    // (1, 0) = 3 (1, 1) = 4 (1, 2) = 5
                    // (2, 0) = 6 (2, 1) = 7 (2, 2) = 8
                    // 其中j/3可以得到块的列坐标
                    // i/3可以得到块的横坐标
                    // 从左到右枚举：
                    // 第一列：0+0，0+1，0+2
                    // 第二列：3+0，3+1，3+2
                    // 第三列：6+0，6+1，6+2
                    // 对应的坐标就是：(i/3)*3 + (j/3)
                    int blockIndex = (i / 3) * 3 + (j / 3);
                    if (row[i][num - 1] || column[num - 1][j] || block[blockIndex][num - 1]) {
                        return false;
                    } else {
                        row[i][num - 1] = true;
                        column[num - 1][j] = true;
                        block[blockIndex][num - 1] = true;
                    }
                }
            }
        }
        return true;
    }
}
