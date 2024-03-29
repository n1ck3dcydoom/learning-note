package leetcode.array.easy;

public class _520_DetectCapital {

    /**
     * 全部字母都是大写，比如 "USA" 。
     * 单词中所有字母都不是大写，比如 "leetcode" 。
     * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。
     * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/detect-capital
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static void main(String[] args) {
        System.out.println(detectCapitalUse("USA"));
        System.out.println(detectCapitalUse("leetcode"));
        System.out.println(detectCapitalUse("Google"));
        System.out.println(detectCapitalUse("FlaG"));
    }

    public static boolean detectCapitalUse(String word) {
        // 统计大写字符出现的个数
        int upperCase = 0;
        for (char c : word.toCharArray()) {
            if (Character.isUpperCase(c)) {
                upperCase++;
            }
        }
        int n = word.length();
        if (upperCase == n) {
            return true;
        }
        return (upperCase == 1 && Character.isUpperCase(word.charAt(0))) || upperCase == 0;
    }
}
