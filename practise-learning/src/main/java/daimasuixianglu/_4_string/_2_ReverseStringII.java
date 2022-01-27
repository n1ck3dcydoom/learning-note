package daimasuixianglu._4_string;

public class _2_ReverseStringII {

    public static void main(String[] args) {
        String s = "abcdefg";
        System.out.println(reverseStr(s, 2));
    }

    public static String reverseStr(String s, int k) {
        int n = s.length();
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; i += 2 * k) {
            // 1.剩余数量大于2K个,反转前k个数
            if (n - i >= 2 * k) {
                reverse(cs, i, i + k - 1);
            }
            // 2.剩余数量小于2k但是大于等于k，反转前k个数
            else if (n - i < 2 * k && n - i >= k) {
                reverse(cs, i, i + k - 1);
            }
            // 3. 剩余数量小于k个，反转剩下的所有字符
            else {
                reverse(cs, i, n - 1);
            }
        }
        return new String(cs);
    }

    private static void reverse(char[] cs, int s, int e) {
        while (s < e) {
            char c = cs[s];
            cs[s] = cs[e];
            cs[e] = c;
            s++;
            e--;
        }
    }
}
