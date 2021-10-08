package algorithm.leetcode.primeDp.day17;

public class _5_LongestPalindromicSubstring {

    public static void main(String[] args) {
        String s = "aaaa";
        System.out.println(longestPalindrome(s));
    }

    public static String longestPalindrome(String s) {
        // 遍历s的所有字符，从位置i开始往两端扩散检索是否能够构成回文子串
        // 从位置i开始往两端扩散前，需要把位置i左右两侧相同的字符全部预处理好
        int n = s.length();
        char[] chars = s.toCharArray();

        int maxLen = 1;
        int start = 0;
        int end = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            int l = i, r = i;
            // 预处理位置i左右两侧相同的字符
            while (l > 0 && chars[l - 1] == ch) {
                l--;
            }
            while (r < n - 1 && chars[r + 1] == ch) {
                r++;
            }
            while (l > 0 && r < n - 1) {
                if (chars[l - 1] == chars[r + 1]) {
                    l--;
                    r++;
                } else {
                    // l和r之间不再能够构成新的回文子串
                    break;
                }
            }
            // 更新最大回文子串长度
            if (r - l + 1 > maxLen) {
                start = l;
                end = r;
                maxLen = r - l + 1;
            }
        }
        return s.substring(start, end + 1);
    }

    public static String longestPalindrome1(String s) {
        int n = s.length();
        // 第一步、定义dp数组
        // 定义dp[i][j]表示从i~j的子串是否是回文子串，其中 0 <= i <= j < n
        boolean[][] dp = new boolean[n][n];

        // 第二步、初始值
        // 只有1个字符时，一定能够构成回文子串
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // 第三步、状态转移
        // 考虑从i开始到j的子串，如果两端前面能够构成回文子串，且s[i]=s[j]，那么i到j也能够成回文子串
        // dp[i][j] = dp[i+1][j-1] && s[i]=s[j]

        // 最大长度至少为1
        int maxLen = 1;
        int start = 0;
        int end = 0;
        // 开始打表，由于 i < j，只用填右上部分表格
        // 实际打表的过程发现，当前层依赖下一层的结果，所以层序倒着打表
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                // 长度只有2的时候，只需要比较s[i]和s[j]就行
                if (j - i == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    // 更新最大长度的两端坐标
                    maxLen = j - i + 1;
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }
}