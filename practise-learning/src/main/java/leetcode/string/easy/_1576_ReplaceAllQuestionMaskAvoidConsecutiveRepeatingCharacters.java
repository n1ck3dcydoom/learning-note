package leetcode.string.easy;

/**
 * Created by n!Ck
 * Date: 2022/1/5
 * Time: 0:20
 * Description:
 */

public class _1576_ReplaceAllQuestionMaskAvoidConsecutiveRepeatingCharacters {
    public String modifyString(String s) {
        // 从左往右直接扫描
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 0; i < n; i++) {
            // 检查是否是问号
            if ('?' == cs[i]) {
                char c = 'a';
                // 检查左右两侧
                while ((i > 0 && cs[i - 1] == c) || (i < n - 1 && cs[i + 1] == c)) {
                    c = (char) (c + 1);
                }
                cs[i] = c;
            }
        }
        return new String(cs);
    }
}
