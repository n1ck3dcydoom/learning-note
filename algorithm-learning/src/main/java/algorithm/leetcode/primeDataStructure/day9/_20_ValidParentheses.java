package algorithm.leetcode.primeDataStructure.day9;

import java.util.ArrayDeque;
import java.util.Deque;

public class _20_ValidParentheses {

    public static void main(String[] args) {
        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "(]";
        String s4 = "([)]";
        String s5 = "{[]}";
        System.out.println(isValid(s1));
        System.out.println(isValid(s2));
        System.out.println(isValid(s3));
        System.out.println(isValid(s4));
        System.out.println(isValid(s5));

    }

    public static boolean isValid(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if ('(' == ch || '[' == ch || '{' == ch) {
                stack.addLast(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (')' == ch && stack.peekLast() == '(') {
                    stack.pollLast();
                } else if (']' == ch && stack.peekLast() == '[') {
                    stack.pollLast();
                } else if ('}' == ch && stack.peekLast() == '{') {
                    stack.pollLast();
                } else {
                    return false;
                }
            }

        }
        return stack.isEmpty();
    }
}
