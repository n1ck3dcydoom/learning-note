package leetcode.simulate.medium;

public class _794_ValidTicTacToeState {

    public static void main(String[] args) {
        String[] b1 = new String[]{"O  ", "   ", "   "};
        String[] b2 = new String[]{"XOX", "X  ", "   "};
        String[] b3 = new String[]{"OOO", "   ", "XXX"};
        String[] b4 = new String[]{"XOX", "O O", "XOX"};

        System.out.println(validTicTacToe(b1));
        System.out.println(validTicTacToe(b2));
        System.out.println(validTicTacToe(b3));
        System.out.println(validTicTacToe(b4));
    }

    public static boolean validTicTacToe(String[] board) {
        // X先走，所以结束时X只能和O相等或者比O多一个，其他情况false
        int x = count(board, 'X');
        int o = count(board, 'O');
        if (x < o || x - o > 1) {
            return false;
        }
        // X和O不能同时获胜，返回false
        if (win(board, 'X') && win(board, 'O')) {
            return false;
        }
        // X获胜时，必须比O多一个，否则返回false
        if (win(board, 'X')) {
            return x - o == 1;
        }
        // O获胜时，必须和X相等，否则返回false
        if (win(board, 'O')) {
            return x == o;
        }
        // 其他情况都是true
        return true;
    }

    private static int count(String[] board, char type) {
        int count = 0;
        for (String line : board) {
            for (int i = 0; i < line.length(); i++) {
                if (type == line.charAt(i)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean win(String[] board, char type) {
        // 检查三行
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == type && board[i].charAt(1) == type && board[i].charAt(2) == type) {
                return true;
            }
        }
        // 检查三列
        for (int i = 0; i < 3; i++) {
            if (board[0].charAt(i) == type && board[1].charAt(i) == type && board[2].charAt(i) == type) {
                return true;
            }
        }
        // 检查对角线
        if (board[0].charAt(0) == type && board[1].charAt(1) == type && board[2].charAt(2) == type) {
            return true;
        }
        return board[0].charAt(2) == type && board[1].charAt(1) == type && board[2].charAt(0) == type;
    }
}
