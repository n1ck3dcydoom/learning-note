package daimasuixianglu._5_stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class _5_EvaluateReversePolishNotation {

    public static void main(String[] args) {
        String[] tokens = new String[]{"4", "13", "5", "/", "+"};
        System.out.println(evalRPN(tokens));
    }

    public static int evalRPN(String[] tokens) {
        // 遇到数字压栈，遇到符号弹出栈顶两个数字并计算，结果继续压入栈
        Deque<Integer> stack = new ArrayDeque<>();
        for (String str : tokens) {
            if ("+".equals(str) || "-".equals(str) || "*".equals(str) || "/".equals(str)) {
                int b = stack.pollLast();
                int a = stack.pollLast();
                int t;
                switch (str) {
                    case "+":
                        t = a + b;
                        break;
                    case "-":
                        t = a - b;
                        break;
                    case "*":
                        t = a * b;
                        break;
                    default:
                        t = a / b;
                        break;
                }
                stack.addLast(t);
            } else {
                stack.addLast(Integer.parseInt(str));
            }
        }
        return stack.pollLast();
    }
}
