package nowcode.top101;

import java.util.Arrays;

/**
 * Created by n!Ck
 * Date: 2022/11/20
 * Time: 19:39
 * Description:
 */

public class _86_大数加法 {

    public static void main(String[] args) {
        System.out.println(solve("0", "0"));
    }

    public static String solve(String s, String t) {
        if (s == null || s.length() == 0) {
            return t;
        }
        if (t == null || t.length() == 0) {
            return s;
        }
        if (s.length() < t.length()) {
            String tmp = s;
            s = t;
            t = tmp;
        }

        int m = s.length();
        int n = t.length();
        char[] cs = s.toCharArray();
        char[] ts = t.toCharArray();


        int carry = 0;
        char[] res = new char[m + 1];
        int i = m - 1, j = n - 1, k = m;

        // 遍历较短的
        while (j >= 0) {
            int tmp = cs[i--] - '0' + ts[j--] - '0' + carry;
            carry = tmp / 10;
            // 取个位
            res[k--] = (char) ((tmp % 10) + '0');
        }
        // 加上长的剩下部分
        while (i >= 0) {
            int tmp = cs[i--] - '0' + carry;
            carry = tmp / 10;
            // 取个位
            res[k--] = (char) ((tmp % 10) + '0');
        }
        // 判断进位
        if (carry == 1) {
            res[0] = '1';
            return new String(res);
        } else {
            return new String(Arrays.copyOfRange(res, 1, res.length));
        }
    }
}
