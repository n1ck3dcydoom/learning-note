package daimasuixianglu._4_string;

public class _7_RepeatedSubstringPattern {

    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern1("abcabcabcabc"));
    }

    public static boolean repeatedSubstringPattern(String s) {
        // 暴力查找，枚举每一个可能重复出现的子串
        int n = s.length();
        // 重复子串长度，至少需要重复一次，则子串长度必然小于等于主串长度的一半
        for (int i = 1; i <= n / 2; i++) {
            // 主串长度能够整除子串长度，则有可能子串是一个重复序列
            if (n % i == 0) {
                // 子串长度i将主串划分为n/i个子序列，检查每个子序列是否相等
                // 当前子序列
                String tmp = s.substring(0, i);
                int j = 0;
                for (; j <= n - i; j += i) {
                    // 比较主串的每个分割子序列是否等于子串
                    int p = 0, q = 0;
                    String sub = s.substring(j, j + i);
                    while (p < i && q < i && sub.charAt(p) == tmp.charAt(q)) {
                        p++;
                        q++;
                    }
                    if (q != i) {
                        break;
                    }
                }
                if (j == n) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean repeatedSubstringPattern1(String s) {
        // 暴力查找，枚举每一个可能重复出现的子串
        int n = s.length();
        // 重复子串长度，至少需要重复一次，则子串长度必然小于等于主串长度的一半
        for (int i = 1; i <= n / 2; i++) {
            // 主串长度能够整除子串长度，则有可能子串是一个重复序列
            if (n % i == 0) {
                boolean match = true;
                // 可能的子序列长度为i
                // 对于数组索引来说
                // 第一个子序列索引范围[0,i-1]
                // 第二个子序列索引范围[i,2i-1]
                // 第三个子序列索引范围[2i,3i-1]
                // 第j个子序列索引范围在[(j-1)i,ji-1]
                for (int j = i; j < n; j++) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }
}
