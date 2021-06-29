package leetcode.easy;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 * <p>
 * 例如：
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：columnNumber = 1
 * 输出："A"
 * 示例 2：
 * <p>
 * 输入：columnNumber = 28
 * 输出："AB"
 * 示例 3：
 * <p>
 * 输入：columnNumber = 701
 * 输出："ZY"
 * 示例 4：
 * <p>
 * 输入：columnNumber = 2147483647
 * 输出："FXSHRXW"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= columnNumber <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-title
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/6/29 20:23
 **/
public class _168_ExcelSheetColumnTitle {

    public static void main(String[] args) {
        int n = 2147483647;
        System.out.println(convertToTitle(n));
    }

    public static String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            sb.append((char) ((columnNumber - 1) % 26 + 'A'));
            columnNumber = (columnNumber - 1) / 26;
        }
        sb.reverse();
        return sb.toString();
    }
}