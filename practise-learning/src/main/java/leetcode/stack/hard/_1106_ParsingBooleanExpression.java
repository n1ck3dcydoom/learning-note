package leetcode.stack.hard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by n!Ck
 * Date: 2022/11/5
 * Time: 00:15
 * Description:
 */

public class _1106_ParsingBooleanExpression {

    public static void main(String[] args) {
        // System.out.println(parseBoolExpr("!(f)"));
        // System.out.println(parseBoolExpr("|(f,t)"));
        // System.out.println(parseBoolExpr("&(t,f)"));
        System.out.println(parseBoolExpr("|(&(t,f,t),!(t))"));
    }

    public static boolean parseBoolExpr(String expression) {
        // 使用栈来模拟计算过程
        // 遇到运算符,压入栈内,遇到 '(' 也压入栈内
        // 如果遇到布尔值,继续压入栈内
        // 如果遇到 ')' 则开始弹出栈内的元素,直到弹出 '(' ,此时栈顶一定是一个运算符
        // 将栈顶运算符和弹出的所有布尔值做计算得到新的布尔值,压入栈
        // 重复这个过程,直到所有栈里面所有运算符都处理完,此时栈里面就是最后的计算结果
        Deque<Character> stack = new ArrayDeque<>();
        char[] cs = expression.toCharArray();
        for (char c : cs) {
            // 运算符
            // 左括号
            // 布尔值
            if (c == '!' || c == '&' || c == '|' || c == '(' || c == 't' || c == 'f') {
                stack.addLast(c);
            }
            // 右括号
            else if (c == ')') {
                // 弹出所有布尔值,直到栈顶是'('
                List<Boolean> list = new ArrayList<>();
                while (!stack.isEmpty() && stack.peekLast() != '(') {
                    list.add(stack.pollLast() == 't');
                }
                // 弹出栈顶的左括号
                stack.pollLast();
                // 弹出栈顶的运算符
                char operator = stack.pollLast();
                if (operator == '!') {
                    // 非运算
                    stack.offerLast(list.get(0) ? 'f' : 't');
                } else if (operator == '&') {
                    // 与运算
                    // 有一个 f 最终结果就是 f
                    boolean f = true;
                    for (Boolean b : list) {
                        if (!b) {
                            f = false;
                            break;
                        }
                    }
                    stack.offerLast(f ? 't' : 'f');
                } else if (operator == '|') {
                    // 或运算
                    // 有一个 t 最终的原酸结果就是 t
                    boolean f = false;
                    for (Boolean b : list) {
                        if (b) {
                            f = true;
                            break;
                        }
                    }
                    stack.offerLast(f ? 't' : 'f');
                }
            }
        }
        // 最后栈顶剩余一个布尔值就是最终的运算结果
        return stack.pollLast() == 't';
    }
}
