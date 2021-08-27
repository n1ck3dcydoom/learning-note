package algorithm.leetcode.primeAlgorithm.day4;

public class _557_ReverseWordInString_III {

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        String rs = reverseWords(s);
        System.out.println(rs);
    }

    public static String reverseWords(String s) {
        if (s == null || "".equals(s)) {
            return "";
        }
        String[] splits = s.split(" ");
        String[] newSplits = new String[splits.length];
        for (int i = 0; i < splits.length; i++) {
            char[] sChar = splits[i].toCharArray();
            reverseString0(sChar);
            newSplits[i] = new String(sChar);
        }
        return String.join(" ", newSplits);
    }

    private static void reverseString0(char[] s) {
        // 双指针直接交换头尾元素
        if (s == null || s.length == 0) {
            return;
        }
        int n = s.length;
        int head = 0, tail = n - 1;
        // 奇数项最后head和tail相遇，不再交换
        // 偶数项最后head=tail-1，不在交换
        while (head < tail) {
            char temp = s[tail];
            s[tail] = s[head];
            s[head] = temp;
            head++;
            tail--;
        }
    }
}
