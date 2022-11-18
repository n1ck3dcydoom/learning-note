package nowcode.top101;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by n!Ck
 * Date: 2022/11/18
 * Time: 11:36
 * Description:
 */

public class _44_有效括号对 {

    public boolean isValid(String s) {
        // 栈保存,遇到右括号就出栈
        Deque<Character> stack = new ArrayDeque<>();
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if ('(' == c || '[' == c || '{' == c) {
                stack.offerLast(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char pop = stack.pollLast();
                if (')' == c && pop != '(') {
                    return false;
                } else if (']' == c && pop != '[') {
                    return false;
                } else if ('}' == c && pop != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
