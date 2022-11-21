package nowcode.top101;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by n!Ck
 * Date: 2022/11/20
 * Time: 16:35
 * Description:
 */

public class _77_最长括号子串 {

    public int longestValidParentheses(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        // 记录最后一次合法括号的起点
        int start = -1;
        stack.offerLast(-1);
        int res = 0;
        for (int i = 0; i < n; i++) {
            // 遇到左括号直接入栈
            if (cs[i] == '(') {
                stack.offerLast(i);
            } else {
                // 遇到右括号
                stack.pollLast();
                // 如果栈已经空，则说明是非法右括号，更新最后一次合法括号的起点为当前位置，表明 start 之前所有位置都非法
                if (stack.isEmpty()) {
                    stack.offerLast(i);
                } else {
                    // 计算当前合法连续括号的长度
                    res = Math.max(res, i - stack.peekLast());
                }
            }
        }
        return res;
    }
}
