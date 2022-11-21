package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/20
 * Time: 19:12
 * Description:
 */

public class _84_最长公共前缀 {

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"abca", "abc", "abca", "abc", "abcc"}));
    }

    public static String longestCommonPrefix(String[] strs) {

        // 遍历每个字符串比较
        int n = strs.length;
        if (n == 0) {
            return "";
        }

        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            // 遍历其他字符串
            for (int j = 1; j < n; j++) {
                // 到达末尾，或者与上一个字符串不相同
                if (i == strs[j].length() || c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        // 所有的字符串都遍历完，而且都相同
        return strs[0];
    }
}
