package leetcode.medium.array;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * <p>
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行Z 字形排列。
 * <p>
 * 比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 * <p>
 * 输入：s = "A", numRows = 1
 * 输出："A"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/4/13 22:47
 **/
public class _6_ZigZagConversion {

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        System.out.println(convert(s, 3));
    }


    public static String convert(String s, int numRows) {
        // 很容易知道行数 = numRows
        // 但是列数不清楚，通过简单计算得到

        // Z 字形的行数为1，直接返回s串
        if (numRows == 1) {
            return s;
        }

        // s串的长度
        int n = s.length();


        // 一个完整的Z含有的字符个数
        int zCount = 2 * numRows - 2;
        // 一个完整的Z能提供的列数
        int zColumn = numRows - 1;

        // 总列数计算
        // 完整的Z个数
        int zTotal = n / zCount;
        // 剩下的零散字符个数
        int zLeast = n % zCount;

        // 总列数
        int numColumns = 0;
        numColumns = zTotal * zColumn + (zLeast < numRows ? 1 : 1 + zLeast - numRows);
        // 行数 numRows

        char[][] grid = new char[numRows][numColumns];


        // 遍历s串，完成grid的填表
        char[] sChar = s.toCharArray();
        int count = 0;

        // 先遍历列
        for (int j = 0; j < numColumns; j++) {
            // 再遍历行
            for (int i = 0; i < numRows && count < n; i++) {
                // 判断是处于竖列，还是斜着的列
                if (j % (numRows - 1) == 0) {
                    // 填完整行
                    grid[i][j] = sChar[count++];
                } else {
                    // 填 row-[j%(row-1)] -1 行
                    grid[numRows - (j % (numRows - 1)) - 1][j] = sChar[count++];
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (grid[i][j] != 0) {
                    sb.append(grid[i][j]);
                }
            }
        }

        return sb.toString();
    }
}