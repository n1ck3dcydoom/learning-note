package leetcode.dp;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description zl
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * <p>
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * <p>
 * 进阶：
 * <p>
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * 两个字符串都只由小写字符组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/is-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/1/11 20:40
 **/
public class _392_Is_Subsequence {
    /**
     * 双指针法
     */
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (t == null || t.length() == 0) {
            return false;
        }
        int sn = s.length();
        int tn = t.length();
        int i = 0;
        int j = 0;
        while (j < tn && i < sn) {
            if (t.charAt(j) == s.charAt(i)) {
                i++;
            }
            j++;
        }
        // 直到指针j或者指针i达到t或者s的尾端
        // 只有指针i等于s的长度才表示找到了
        // 最后一个字符的下标是小于sn长度的，只有最后一个字符匹配上了i++，这个时候i的下标才等于sn长度
        return i == sn;
    }
}