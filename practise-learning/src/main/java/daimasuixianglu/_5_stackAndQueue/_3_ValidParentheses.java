package daimasuixianglu._5_stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class _3_ValidParentheses {

    public static void main(String[] args) {
        String s1 = "()[]{}";
        String s2 = "(]";
        String s3 = "([)]";
        String s4 = "{[]}";
        System.out.println(isValid(s1));
        System.out.println(isValid(s2));
        System.out.println(isValid(s3));
        System.out.println(isValid(s4));
    }

    public static boolean isValid(String s) {
        // 栈
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if ('(' == c || '[' == c || '{' == c) {
                stack.addLast(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pollLast();
                if (')' == c) {
                    if ('(' != top) {
                        return false;
                    }
                } else if (']' == c) {
                    if ('[' != top) {
                        return false;
                    }
                } else {
                    if ('{' != top) {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}
