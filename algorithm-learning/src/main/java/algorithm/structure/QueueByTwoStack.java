package algorithm.structure;

import java.util.Stack;

/**
 * Created by n!Ck Date: 2018/10/7 Time: 13:45 Description:
 */
public class QueueByTwoStack {
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    public QueueByTwoStack() {
    }

    public void push(Integer e) {
        stack1.push(e);
    }

    public Integer pop() throws Exception {
        // 如果s2不为空则直接出栈
        if (!stack2.empty()) {
            return stack2.pop();
        }
        // 如果s2为空且s1不为空，则先将s1的所有元素弹出后压入s2
        else if (!stack1.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        } else {
            throw new Exception("Empty pop");
        }
    }

    public boolean isEmpty() {
        return stack1.empty() && stack2.empty();
    }
}
