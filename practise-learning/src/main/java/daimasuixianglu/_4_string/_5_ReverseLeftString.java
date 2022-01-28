package daimasuixianglu._4_string;

public class _5_ReverseLeftString {
    public String reverseLeftWords(String s, int n) {
        char[] cs = s.toCharArray();
        // 翻转前k个字符
        reverse(cs, 0, n - 1);
        // 翻转后n-k个字符
        reverse(cs, n, s.length() - 1);
        // 翻转整个字符串
        reverse(cs, 0, s.length() - 1);
        return new String(cs);
    }

    private void reverse(char[] cs, int s, int e) {
        while (s < e) {
            char temp = cs[s];
            cs[s] = cs[e];
            cs[e] = temp;
            s++;
            e--;
        }
    }
}
