package leetcode.array.easy;

public class _1816_TruncateSentence {

    public static void main(String[] args) {
        String s1 = "Hello how are you Contestant";
        String s2 = "What is the solution to this problem";
        String s3 = "chopper is not a tanuki";
        String s4 = "test";

        System.out.println(truncateSentence1(s1, 4));
        System.out.println(truncateSentence1(s2, 4));
        System.out.println(truncateSentence1(s3, 5));
        System.out.println(truncateSentence1(s4, 1));
    }

    public static String truncateSentence(String s, int k) {
        // api战士?
        String[] split = s.split(" ");
        if (split.length == k) {
            return s;
        }
        String[] rs = new String[k];
        for (int i = 0; i < k; i++) {
            rs[i] = split[i];
        }
        return String.join(" ", rs);
    }

    public static String truncateSentence1(String s, int k) {
        // 预处理s末尾添加一个空格
        s = s + " ";
        // 从前往后找空格做字符串切割2
        int pos = 0;
        for (; pos < s.length() && k > 0; pos++) {
            if (s.charAt(pos) == ' ') {
                k--;
            }
        }
        return s.substring(0, pos - 1);
    }
}
