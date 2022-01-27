package daimasuixianglu._5_stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class _4_RemoveAllAdjacentDuplicatesInString {

    public static void main(String[] args) {
        String s = "abbaca";
        System.out.println(removeDuplicates(s));
    }

    public static String removeDuplicates(String s) {
        // 栈
        Deque<Character> stack = new ArrayDeque<>();
        // 遍历字符，如果遇到了与栈顶元素相同的字符，弹出栈顶元素
        for (char c : s.toCharArray()) {
            if (stack.isEmpty() || stack.peekLast() != c) {
                stack.addLast(c);
            } else {
                while (!stack.isEmpty() && stack.peekLast() == c) {
                    stack.pollLast();
                }
            }
        }
        char[] chars = new char[stack.size()];
        int pos = 0;
        while (!stack.isEmpty()) {
            chars[pos++] = stack.pollFirst();
        }
        return new String(chars);
    }
}
