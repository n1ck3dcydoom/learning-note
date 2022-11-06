package leetcode.array.easy;

/**
 * Created by n!Ck
 * Date: 2022/11/6
 * Time: 23:28
 * Description:
 */

public class _1678_Goal_Parser_Interpretation {

    public String interpret(String command) {
        // 直接模拟
        StringBuilder sb = new StringBuilder();

        char[] cs = command.toCharArray();
        int n = cs.length;
        for (int i = 0; i < n; i++) {
            if ('G' == cs[i]) {
                sb.append("G");
            } else if ('(' == cs[i]) {
                // 判断 ()
                if (')' == cs[i + 1]) {
                    sb.append("o");
                    i++;
                } else {
                    sb.append("al");
                    i += 2;
                }
            }
        }

        return sb.toString();
    }
}
