package algorithm.leetcode.primeAlgorithm.day4;

public class _344_ReverseString {
    public static void main(String[] args) {
        char[] chars1 = new char[] { 'h', 'e', 'l', 'l', 'o' };
        char[] chars2 = new char[] { 'H', 'a', 'n', 'n', 'a', 'h' };

        reverseString(chars1);
        reverseString(chars2);
        for (int i = 0; i < chars1.length; i++) {
            System.out.print(chars1[i] + " ");
        }
        for (int i = 0; i < chars2.length; i++) {
            System.out.print(chars2[i] + " ");
        }
    }

    public static void reverseString(char[] s) {
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
